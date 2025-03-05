// ProductService.java (Servicio de Dominio)
package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setMinimumStockLevel(Product product, int minimumStockLevel) {
        // Validar que el nivel mínimo sea mayor que cero
        if (minimumStockLevel <= 0) {
            throw new IllegalArgumentException("El nivel mínimo de stock debe ser mayor que cero.");
        }

        // Establecer el nivel mínimo de stock
        product.setMinimumStockLevel(minimumStockLevel);

        // Guardar el producto actualizado en el repositorio
        productRepository.save(product);
    }
}