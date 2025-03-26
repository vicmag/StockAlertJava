package com.store.domain.model;

import lombok.Data;

@Data
public class Product {
    String name;
    int minimumStockLevel;

    public Product(String name){
        this.name = name;
    }

}
