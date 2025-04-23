package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void setMinimumStockLevel(Product product, int minimuStockLevel){
        if (minimuStockLevel < 0){
            throw new IllegalArgumentException("El umbral debe ser positivo.");
        }
        product.setMinimumStockLevel(minimuStockLevel);
        saveProduct(product);
    }

    private void saveProduct(Product product){
        productRepository.save(product);
    }

}
