package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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

    @Test
    void whenSetMinimumStockLevelWithInvalidValue_thenThrowException(){        
        //Arrage
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int invalidMinimumSotckLevel = -1;
        

        //Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> productService.setMinimumStockLevel(product, invalidMinimumSotckLevel));
        
        verify(productRepository, never()).save(product);
                
    }
    
}
