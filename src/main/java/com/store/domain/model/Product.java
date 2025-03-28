package com.store.domain.model;

import lombok.Data;

@Data
public class Product {
    String name;
    int stock;
    int minimumStockLevel;

    public Product(String name, int stock){
        this.name = name;
        this.stock = stock;
    }

}
