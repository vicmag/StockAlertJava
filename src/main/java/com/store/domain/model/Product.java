package com.store.domain.model;

import lombok.Data;

@Data
public class Product {
    private String name;
    private int minimumStockLevel;

    public Product(String name){
        this.name = name;
    }

}
