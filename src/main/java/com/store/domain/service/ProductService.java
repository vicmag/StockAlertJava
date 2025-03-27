package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void setMinimumStockLevel(Product product, int minimumStockLevel){
        //Implementación
        
        //Validación del stock mínimo
        if (minimumStockLevel < 0){
            throw new IllegalArgumentException("El valor es negativo");
        }

        product.setMinimumStockLevel(minimumStockLevel);

        //Almaceno en BD
        productRepository.save(product);

    }

}
