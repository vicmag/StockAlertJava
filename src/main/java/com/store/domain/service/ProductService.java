package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
       this.productRepository = productRepository; 
    }

    public void saveProduct(Product product){

        //Validación de producto
        validateProduct(product);

        //Se almacena el valor en BD
        productRepository.save(product);
    }

    private void validateProduct(Product product){
        if (product.getStock() < 0){
            throw new IllegalArgumentException("El valor debe ser positivo.");
        }
    }

    public void setMinimumStockLevel(Product product, int minimumStockLevel){

        validateMiminumStockLeven(minimumStockLevel);

        //Se guarda el valor en el modelo
        product.setMinimumStockLevel(minimumStockLevel);
        
        saveProduct(product);
        
    }    

    private void validateMiminumStockLeven(int minimumStockLevel){
        if (minimumStockLevel <= 0){
            throw new IllegalArgumentException("El valor debe ser positivo.");
        }
    }
}
