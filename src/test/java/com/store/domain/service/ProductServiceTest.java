package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class ProductServiceTest {

    @Test
    void whenSetMinimumStockLevel_thenLevelIsSaved(){
        //Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int newMinimumStockLevel = 15;

        //Act
        productService.setMinimumStockLevel(product, newMinimumStockLevel);

        //Assert
        verify(productRepository).save(product);
        assertEquals(newMinimumStockLevel, product.getMinimumStockLevel());
    }

    @Test
    void whenMinimumStockLevelIsBelowZero_thenThrowException(){
        //Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int invalidMinimumStockLevel = -1;
    
        //Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> productService.setMinimumStockLevel(product, invalidMinimumStockLevel)
        );

        verify(productRepository, never()).save(product);
    }

    @Test
    void whenMinimumStockLevelIsBelowZero_thenThrowException(){
        //Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int invalidMinimumStockLevel = -1;
    
        //Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> productService.setMinimumStockLevel(product, invalidMinimumStockLevel)
        );

        verify(productRepository, never()).save(product);
    }

    @Test
    void whenMinimumStockLevelIsEqualToZero_thenThrowException(){
        //Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int invalidMinimumStockLevel = 0;
    
        //Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> productService.setMinimumStockLevel(product, invalidMinimumStockLevel)
        );

        verify(productRepository, never()).save(product);
    }
}
