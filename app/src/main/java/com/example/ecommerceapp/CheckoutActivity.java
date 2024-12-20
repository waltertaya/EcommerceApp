package com.example.ecommerceapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Retrieve the cart from the intent
        cart = (Cart) getIntent().getSerializableExtra("cart");

        // Display order summary
        TextView orderSummaryTextView = findViewById(R.id.order_summary);
        orderSummaryTextView.setText("Order Total: KSh " + cart.getTotalPrice());

        // Confirm button action
        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> {
            // Placeholder for order confirmation logic
            orderSummaryTextView.setText("Order Confirmed! Thank you for shopping.");
        });
    }
}