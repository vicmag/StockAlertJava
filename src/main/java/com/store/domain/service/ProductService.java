package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void setMinimumStockLevel(Product product, int minimumStockLevel){    
        
        //Validación del stock mínimo
        validateMinimumStockLevel(minimumStockLevel);

        product.setMinimumStockLevel(minimumStockLevel);

        //Almaceno en BD
        saveProduct(product);

    }    

    private void validateMinimumStockLevel(int minimumStockLevel){
        if (minimumStockLevel <= 0){
            throw new IllegalArgumentException("El valor es negativo");
        }
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

}
