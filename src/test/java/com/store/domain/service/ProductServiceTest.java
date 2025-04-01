package com.store.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.assertEquals;
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
