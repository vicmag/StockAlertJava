package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void increaseStock(String productName, int amount) {
        // Validación básica
        if (amount <= 0) {
            throw new IllegalArgumentException("El incremento debe ser un valor positivo");
        }

        // Obtener producto existente
        Product product = productRepository.findByName(productName);
        
        // Incrementar stock
        int newStock = product.getStock() + amount;
        product.setStock(newStock);
        
        // Guardar cambios
        productRepository.save(product);
    }

}