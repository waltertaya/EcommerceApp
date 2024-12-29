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

public class CheckoutActivity extends AppCompatActivity {

    private RadioGroup paymentMethodGroup;
    private EditText mpesaNumber;
    private Button placeOrderButton;
    private TextView totalPriceTextView;

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
                    } else {
                        Toast.makeText(CheckoutActivity.this, "Order placed successfully! Total: " + totalPrice, Toast.LENGTH_SHORT).show();
                        // Optionally navigate to an order confirmation screen here
                    }
                } else {
                    Toast.makeText(CheckoutActivity.this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
