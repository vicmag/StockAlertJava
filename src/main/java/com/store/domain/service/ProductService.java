package com.store.domain.service;

import com.store.domain.port.AlertNotifier;
import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

public class ProductService {
    private final ProductRepository productRepository;
    private final AlertNotifier alertNotifier;

    public ProductService(ProductRepository productRepository, AlertNotifier alertNotifier){
        this.productRepository = productRepository;
        this.alertNotifier = alertNotifier;
    }

    public void setMinimumStockLevel(Product product, int minimuStockLevel){
        validateMinimumStockLevel(minimuStockLevel);
        product.setMinimumStockLevel(minimuStockLevel);
        saveProduct(product);
    }

    private void saveProduct(Product product){
        productRepository.save(product);
    }

    private void validateMinimumStockLevel(int minimuStockLevel){
        if (minimuStockLevel <= 0){
            throw new IllegalArgumentException("El umbral debe ser positivo.");
        }
    }

    public void checkStockLevel(Product product){
        if (product.getStock() < product.getMinimumStockLevel()){
            alertNotifier.notifyLowStock(product);
        } 
    }

    public void incrementStock(String productName, int increment){
        Product product = productRepository.findByName(productName);
        if (product == null){
            throw new IllegalArgumentException("El producto no existe.");
        }
        product.setStock(product.getStock() + increment);
        productRepository.save(product);        
    }
}
