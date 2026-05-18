package com.example.demo.event;

import com.example.demo.model.Product;

public class ProductCreatedEvent {
    private final Product product;

    public ProductCreatedEvent(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
