package com.example.e_commerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Product> productList = new ArrayList<>(
                Arrays.asList(
                        new Product("Smartwatch", 574, R.drawable.smartwatch),
                        new Product("TV Stand", 6599, R.drawable.tvstand),
                        new Product("Headphones", 1200, R.drawable.headphones),
                        new Product("Bluetooth Speaker", 2500, R.drawable.bluetooth_speaker),
                        new Product("Gaming Chair", 9999, R.drawable.gaming_chair),
                        new Product("Laptop Stand", 1500, R.drawable.laptop_stand),
                        new Product("LED Desk Lamp", 800, R.drawable.led_desk_lamp),
                        new Product("Keyboard", 2999, R.drawable.keyboard),
                        new Product("Mouse", 999, R.drawable.mouse),
                        new Product("Monitor", 14500, R.drawable.monitor),
                        new Product("Office Chair", 8500, R.drawable.office_chair),
                        new Product("Tablet", 25000, R.drawable.tablet),
                        new Product("Smartphone", 35000, R.drawable.smartphone),
                        new Product("Wireless Earbuds", 4000, R.drawable.wireless_earbuds),
                        new Product("External Hard Drive", 5000, R.drawable.external_hard_drive),
                        new Product("Camera", 45000, R.drawable.camera),
                        new Product("Tripod", 1500, R.drawable.tripod),
                        new Product("Printer", 12000, R.drawable.printer),
                        new Product("Scanner", 9000, R.drawable.scanner),
                        new Product("Gaming Console", 55000, R.drawable.gaming_console),
                        new Product("TV", 40000, R.drawable.tv),
                        new Product("Soundbar", 12000, R.drawable.soundbar),
                        new Product("Refrigerator", 65000, R.drawable.refrigerator),
                        new Product("Washing Machine", 35000, R.drawable.washing_machine),
                        new Product("Microwave Oven", 12000, R.drawable.microwave_oven)

                )
        );

        ProductAdapter adapter = new ProductAdapter(this, productList);
        ListView productListView = findViewById(R.id.product_list);
        productListView.setAdapter(adapter);

        // Add click listener for Cart icon
        ImageView cartIcon = findViewById(R.id.cart_icon);
        cartIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
