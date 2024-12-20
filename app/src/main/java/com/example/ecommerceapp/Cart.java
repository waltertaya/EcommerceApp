package com.example.ecommerceapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<Product> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cartItems.add(product);
    }

    public void removeFromCart(Product product) {
        cartItems.remove(product);
    }

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }
}
