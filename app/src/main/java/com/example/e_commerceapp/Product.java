package com.example.e_commerceapp;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int price;
    private int imageResource;

    public Product(String name, int price, int imageResource) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }
}
