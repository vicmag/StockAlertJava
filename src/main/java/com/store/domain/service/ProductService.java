package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

public class ProductService {
    private final ProductRepository productRepository;
    
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void setMinimumStockLevel(Product product, int minimumStockLevel){
        //Validamos el valor mínimo del stock
        validateMinimumStockLevel(minimumStockLevel);


        //Establecemos el valor mínimo del stock
        product.setMinimumStockLevel(minimumStockLevel);

        //Guardo el producto en la infraestructura
        productRepository.save(product);
    }

    public void validateMinimumStockLevel(int minimumStockLevel){
        if  (minimumStockLevel <= 0 ){
            throw new IllegalArgumentException("El nivel mínimo debe ser mayor a cero.");
        }
    }


}
