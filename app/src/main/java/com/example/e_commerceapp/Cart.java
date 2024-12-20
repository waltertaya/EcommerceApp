package com.example.e_commerceapp;

import java.util.ArrayList;

public class Cart {
    private static Cart instance;
    private ArrayList<Product> cartItems;

    private Cart() {
        cartItems = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addProduct(Product product) {
        cartItems.add(product);
    }

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product) {
        cartItems.add(product);
    }
}
