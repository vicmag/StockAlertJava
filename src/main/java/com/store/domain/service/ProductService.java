package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void setMinimumStockLevel(Product product, int minimumStockLevel){
        
        //Valida el valor del stock mínimo
        validateMinimumStockLevel(minimumStockLevel);

        //Establece el valor de stock minimo
        product.setMinimumStockLevel(minimumStockLevel);

        //Guardado del producto
        saveProduct(product);
    }

    public void saveProduct(Product product) {
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("El stock no debe ser negativo.");
        }
        productRepository.save(product); 
    }

    private void validateMinimumStockLevel(int minimumStockLevel){
        if (minimumStockLevel <= 0){
            throw new IllegalArgumentException("El valor es negativo.");
        }
    }

}
