package com.example.e_commerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private TextView tvTotalPrice;
    private EditText etShippingAddress;
    private Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize UI components
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        etShippingAddress = findViewById(R.id.etShippingAddress);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        // Retrieve total price from Intent
        Intent intent = getIntent();
        String totalPrice = intent.getStringExtra("totalPrice");
        tvTotalPrice.setText("Total Price: $" + totalPrice);

        // Place order button click listener
        btnPlaceOrder.setOnClickListener(view -> {
            String shippingAddress = etShippingAddress.getText().toString();

            if (shippingAddress.isEmpty()) {
                Toast.makeText(this, "Please enter a shipping address", Toast.LENGTH_SHORT).show();
            } else {
                // Order placement logic (mocked)
                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            }
        });
    }
}
