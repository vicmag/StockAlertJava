package com.store.domain.model;

import lombok.Data;

@Data
public class Product {
    String productName;
    int stock;
    int increment;

    public Product(String productName, int initialStock) {
        this.productName = productName;
        this.stock = initialStock;
    }

}
