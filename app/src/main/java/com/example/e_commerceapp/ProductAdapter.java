package com.example.e_commerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private ArrayList<Product> productList;

    public ProductAdapter(Context context, ArrayList<Product> productList) {
        super(context, R.layout.product_item, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        }

        Product product = productList.get(position);

        TextView productNameTextView = convertView.findViewById(R.id.product_name);
        TextView productPriceTextView = convertView.findViewById(R.id.product_price);
        ImageView productImageView = convertView.findViewById(R.id.product_image);
        Button addToCartButton = convertView.findViewById(R.id.add_to_cart_button);

        productNameTextView.setText(product.getName());
        productPriceTextView.setText("KSh " + product.getPrice());
        productImageView.setImageResource(product.getImageResource());

        addToCartButton.setOnClickListener(v -> {
            Cart.getInstance().addProduct(product);
            Toast.makeText(context, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
