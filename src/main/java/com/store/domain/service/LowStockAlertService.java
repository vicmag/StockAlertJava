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
        // Not implemented yet (Red Phase)
        throw new UnsupportedOperationException("Not implemented yet");
    }
}