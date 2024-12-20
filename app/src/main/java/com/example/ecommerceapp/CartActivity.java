package com.example.ecommerceapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

public class CartActivity extends AppCompatActivity {

    private Cart cart;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Retrieve the cart from the intent
//        cart = (Cart) getIntent().getSerializableExtra("cart");
//        if (cart == null) {
//            cart = new Cart(); // Initialize if null to prevent crashes
//        }
        cart = (Cart) getIntent().getSerializableExtra("cart");
        if (cart == null) {
            Log.e("CartActivity", "Cart is null");
            cart = new Cart();  // Initialize to prevent crashes
        } else {
            Log.d("CartActivity", "Cart retrieved: " + cart.getCartItems().size() + " items");
        }


        // Set up the adapter
        cartAdapter = new CartAdapter(this, cart.getCartItems());
        ListView cartListView = findViewById(R.id.cart_list);
        cartListView.setAdapter(cartAdapter);

        // Display total price
        TextView totalPriceTextView = findViewById(R.id.total_price);
        totalPriceTextView.setText("Total: KSh " + cart.getTotalPrice());

        // Button to navigate to checkout page
        Button checkoutButton = findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            intent.putExtra("cart", cart);
            startActivity(intent);
        });
    }
}
