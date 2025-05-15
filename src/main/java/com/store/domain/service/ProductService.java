package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void increaseStock(String productName, int increment){
        // 1. Buscar el producto
        Product product = productRepository.findByName(productName);

        // 2. Incrementar el stock
        product.setStock(product.getStock() + increment);

        // 3. Guardar el producto
        productRepository.save(product);

    }

}
