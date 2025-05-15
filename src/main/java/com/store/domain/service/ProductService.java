package com.store.domain.service;

import com.store.domain.port.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void increaseStock(String productName, int increment){
        // No implementado. Fase Roja
    }

}
