package com.example.e_commerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product("Smartwatch", 574, R.drawable.smartwatch));
        productList.add(new Product("TV Stand", 6599, R.drawable.tvstand));

        ProductAdapter adapter = new ProductAdapter(this, productList);
        ListView productListView = findViewById(R.id.product_list);
        productListView.setAdapter(adapter);

        Button viewCartButton = findViewById(R.id.view_cart_button);
        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

    }
}
