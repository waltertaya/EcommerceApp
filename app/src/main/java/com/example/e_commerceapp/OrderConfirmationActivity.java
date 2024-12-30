package com.example.e_commerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        TextView confirmationMessage = findViewById(R.id.confirmation_message);
        Button backToHomeButton = findViewById(R.id.btn_back_to_home);

        // Set a confirmation message
        confirmationMessage.setText("Your order has been placed successfully!");

        // Redirect to MainActivity when the button is clicked
        backToHomeButton.setOnClickListener(view -> {
            Intent intent = new Intent(OrderConfirmationActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
