package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    @Test
    void whenSetMinimumStockLevel_thenLevelIsSaved(){
        //Arrage
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int newMinimumSotckLevel = 15;

        //Act
        productService.setMinimumStockLevel(product, newMinimumSotckLevel);

        //Assert
        verify(productRepository).save(product);
        assertEquals(newMinimumSotckLevel,  product.getMinimumStockLevel());

    }
    
}
