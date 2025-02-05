package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.repository.ProductRepository;
import java.util.Optional;

public class LowStockAlertService {
    private final ProductRepository productRepository;

    public LowStockAlertService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean checkLowStock(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            return product.getCurrentStock() <= product.getLowStockThreshold();
        }
        return false;
    }

}