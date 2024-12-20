package com.example.ecommerceapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;
    private ArrayList<Product> productList;
    private Cart cart;

    public ProductAdapter(Context context, ArrayList<Product> productList, Cart cart) {
        super(context, R.layout.product_item, productList);
        this.context = context;
        this.productList = productList;
        this.cart = cart;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        }

        // Get the current product
        Product product = productList.get(position);

        // Set product name
        TextView productNameTextView = convertView.findViewById(R.id.product_name);
        productNameTextView.setText(product.getName());

        // Set product price
        TextView productPriceTextView = convertView.findViewById(R.id.product_price);
        productPriceTextView.setText("KSh " + product.getPrice());

        // Set product image
        ImageView productImageView = convertView.findViewById(R.id.product_image);
        productImageView.setImageResource(product.getImageResource());

        // Set up "Add to Cart" button
        Button addToCartButton = convertView.findViewById(R.id.add_to_cart_button);
//        addToCartButton.setOnClickListener(v -> {
//            cart.addToCart(product); // Add product to the cart
//            notifyDataSetChanged(); // Update the adapter
//        });
        addToCartButton.setOnClickListener(v -> {
            cart.addToCart(product);  // Add product to cart
            Log.d("Cart", "Product added to cart: " + product.getName());
            notifyDataSetChanged();  // Notify adapter to refresh the list view
        });


        return convertView;
    }
}
