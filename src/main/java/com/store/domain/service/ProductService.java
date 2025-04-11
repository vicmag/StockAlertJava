package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void increaseStock(String productName, int amount) {
        validateIncrementAmount(amount);
        Product product = getProductByName(productName);
        updateProductStock(product, amount);
    }
    
    private void validateIncrementAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El incremento debe ser un valor positivo");
        }
    }
    
    private Product getProductByName(String name) {
        Product product = productRepository.findByName(name);
        
        return product;
    }
    
    private void updateProductStock(Product product, int increment) {
        product.setStock(calculateNewStock(product.getStock(), increment));
        productRepository.save(product);
    }
    
    private int calculateNewStock(int currentStock, int increment) {
        return currentStock + increment;
    }

}