package com.example.e_commerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CartAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> cartItems;

    public CartAdapter(Context context, List<Product> cartItems) {
        super(context, 0, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }

        Product product = cartItems.get(position);

        TextView productNameTextView = convertView.findViewById(R.id.cart_product_name);
        TextView productPriceTextView = convertView.findViewById(R.id.cart_product_price);
        ImageView productImageView = convertView.findViewById(R.id.cart_product_image);

        productNameTextView.setText(product.getName());
        productPriceTextView.setText("KSh " + product.getPrice());
        productImageView.setImageResource(product.getImageResource());

        return convertView;
    }
}
