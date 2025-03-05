// ProductService.java (Servicio de Dominio - Refactorizado)
package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setMinimumStockLevel(Product product, int minimumStockLevel) {
        validateMinimumStockLevel(minimumStockLevel);
        product.setMinimumStockLevel(minimumStockLevel);
        productRepository.save(product);
    }

    private void validateMinimumStockLevel(int minimumStockLevel) {
        if (minimumStockLevel <= 0) {
            throw new IllegalArgumentException("El nivel mínimo de stock debe ser mayor que cero.");
        }
    }
}