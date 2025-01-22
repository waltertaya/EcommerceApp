package com.example.e_commerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView cartListView = findViewById(R.id.cart_list_view);

        CartAdapter cartAdapter = new CartAdapter(this, Cart.getInstance().getCartItems());
        cartListView.setAdapter(cartAdapter);

        TextView totalText = findViewById(R.id.totalText);

        totalText.setText("Total: Ksh " + String.valueOf( Cart.getInstance().getTotal() ));

        Button btnCheckout = findViewById(R.id.btnCheckout);

        btnCheckout.setOnClickListener(view -> {
            // Calculate total price (mocked as $100 for now)
            String totalPrice = String.valueOf(Cart.getInstance().getTotal());

            // Launch CheckoutActivity
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            intent.putExtra("totalPrice", totalPrice);
            startActivity(intent);
        });

    }
}
