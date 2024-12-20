package com.example.ecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Product> {

    private Context context;
    private ArrayList<Product> cartItems;

    public CartAdapter(Context context, ArrayList<Product> cartItems) {
        super(context, R.layout.cart_item, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }

        // Get the current product
        Product product = cartItems.get(position);

        // Set product name
        TextView productNameTextView = convertView.findViewById(R.id.cart_item_name);
        productNameTextView.setText(product.getName());

        // Set product price
        TextView productPriceTextView = convertView.findViewById(R.id.cart_item_price);
        productPriceTextView.setText("KSh " + product.getPrice());

        // Set product image
        ImageView productImageView = convertView.findViewById(R.id.cart_item_image);
        productImageView.setImageResource(product.getImageResource());

        return convertView;
    }
}
