package com.store.domain.model;

import lombok.Data;

@Data
public class Product {
    private String name;
    private int stock;
    private int minimumStockLevel;
    
    public Product(String name, int stock){
        this.name = name;
        this.stock = stock;
    }

}
