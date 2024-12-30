package com.example.e_commerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.models.AccessTokenResponse;
import com.example.e_commerceapp.models.STKPushRequest;
import com.example.e_commerceapp.services.MpesaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    private RadioGroup paymentMethodGroup;
    private EditText mpesaNumber;
    private Button placeOrderButton;
    private TextView totalPriceTextView;

    private static final String BUSINESS_SHORT_CODE = "174379";
    private static final String PASSKEY = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
    private static final String CALLBACK_URL = "https://webhook.site/6c50c885-4090-48d5-a6cf-c64de7ae9c07";
    private static final String PHONE_NUMBER = "254795529642";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Reference the RadioGroup instead of a RadioButton
        paymentMethodGroup = findViewById(R.id.payment_method_group);
        mpesaNumber = findViewById(R.id.edit_mpesa_number);
        placeOrderButton = findViewById(R.id.btn_place_order);
        totalPriceTextView = findViewById(R.id.total_price_text);

        // Retrieve total price from intent
        Intent intent = getIntent();
        String totalPrice = intent.getStringExtra("totalPrice");
        if (totalPrice != null) {
            totalPriceTextView.setText("KSh " + totalPrice);
        }

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = paymentMethodGroup.getCheckedRadioButtonId();

                if (selectedId != -1) {
                    RadioButton selectedRadio = findViewById(selectedId);
                    String paymentMethod = selectedRadio.getText().toString();
                    String phoneNumber = mpesaNumber.getText().toString();

                    if (paymentMethod.equals("Via M-Pesa") && phoneNumber.isEmpty()) {
                        Toast.makeText(CheckoutActivity.this, "Please enter M-PESA number", Toast.LENGTH_SHORT).show();
                    } else if (paymentMethod.equals("Via M-Pesa")){
                        initiateMpesaPayment(phoneNumber , totalPrice);
                    }
                    else {
                        // Navigate to OrderConfirmationActivity
                        Intent intent = new Intent(CheckoutActivity.this, OrderConfirmationActivity.class);
                        startActivity(intent);
                        finish(); // Finish CheckoutActivity
                    }
                } else {
                    Toast.makeText(CheckoutActivity.this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void initiateMpesaPayment(String phoneNumber , String amount) {
        MpesaService.generateAccessToken(new Callback<AccessTokenResponse>() {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String accessToken = "Bearer " + response.body().getAccessToken();

                    String timestamp = MpesaService.getCurrentTimestamp();
                    String password = MpesaService.generatePassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp);
                    Float floatAmount = Float.parseFloat(amount);
                    Integer intAmount = Math.round(floatAmount);
                    String strAmount = String.valueOf(intAmount);
                    String formattedPhoneNumber = formatPhoneNumber(phoneNumber);

                    STKPushRequest request = new STKPushRequest(
                            BUSINESS_SHORT_CODE,
                            password,
                            timestamp,
                            "CustomerPayBillOnline",
                            strAmount,
                            formattedPhoneNumber,
                            BUSINESS_SHORT_CODE,
                            formattedPhoneNumber,
                            CALLBACK_URL,
                            "Order123",
                            "Payment for Order"
                    );

                    MpesaService.sendSTKPush(accessToken, request, new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(CheckoutActivity.this, "Payment Request Sent", Toast.LENGTH_SHORT).show();
                                // Navigate to OrderConfirmationActivity
                                Intent intent = new Intent(CheckoutActivity.this, OrderConfirmationActivity.class);
                                startActivity(intent);
                                finish(); // Finish CheckoutActivity
                            } else {
                                Toast.makeText(CheckoutActivity.this, "Failed to Send Payment Request", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(CheckoutActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(CheckoutActivity.this, "Failed to get access token", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                Toast.makeText(CheckoutActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static String formatPhoneNumber(String phoneNumber) {
        // Check if the number starts with a 0 and replace it with 254
        if (phoneNumber.startsWith("0")) {
            return "254" + phoneNumber.substring(1);
        }

        // Check if the number starts with + and remove it, then ensure it starts with 254
        if (phoneNumber.startsWith("+")) {
            return "254" + phoneNumber.substring(1);
        }

        // If it already starts with 254, return the number as is
        if (phoneNumber.startsWith("254")) {
            return phoneNumber;
        }

        // Return the phone number as is if no modification is needed
        return phoneNumber;
    }
}