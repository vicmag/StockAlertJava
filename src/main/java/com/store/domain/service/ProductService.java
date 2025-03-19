package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void setMinimumStockLevel(Product product, int newMinimumStockLevel){
        if (newMinimumStockLevel <= 0){
            throw new IllegalArgumentException("El nivel mínimo deber ser mayor a cero.");
        }
        
        product.setMinimumStockLevel(newMinimumStockLevel);

        productRepository.save(product);
    }
    
}
