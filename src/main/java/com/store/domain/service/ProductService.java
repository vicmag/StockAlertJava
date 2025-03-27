package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void setMinimumStockLevel(Product product, int minimumStockLevel){
        //Establece el valor de stock minimo
        product.setMinimumStockLevel(minimumStockLevel);

        //Guardado del producto
        productRepository.save(product);
    }

}
