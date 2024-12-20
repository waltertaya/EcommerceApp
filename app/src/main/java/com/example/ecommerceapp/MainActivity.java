package com.example.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> productList;
    private ProductAdapter productAdapter;
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the cart
        cart = new Cart();

        // Initialize the product list
        productList = new ArrayList<>();
        productList.add(new Product("Smartwatch", 574, R.drawable.smartwatch));
        productList.add(new Product("TV Stand", 6599, R.drawable.tvstand));

        // Set up the adapter
        productAdapter = new ProductAdapter(this, productList, cart);
        ListView productListView = findViewById(R.id.product_list);
        productListView.setAdapter(productAdapter);

        // Button to navigate to the cart page
        Button viewCartButton = findViewById(R.id.view_cart_button);
        viewCartButton.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, CartActivity.class);
//            intent.putExtra("cart", cart);
//            startActivity(intent);
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            intent.putExtra("cart", cart);
            startActivity(intent);

        });
    }
}
