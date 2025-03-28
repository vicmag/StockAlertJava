package com.store.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.store.domain.port.ProductRepository; 
import com.store.domain.model.Product;

public class ProductServiceTest {
    
    @Test
    void whenSetMinimumStockLevel_thenLevelIsSaved(){
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul", 100);
        int newMinimumStockLevel = 15;

        // Act
        productService.setMinimumStockLevel(product, newMinimumStockLevel);

        // Assert
        assertEquals(newMinimumStockLevel, product.getMinimumStockLevel());
        verify(productRepository).save(product);
        
    }

    @Test
    void whenMinimumStockLevelIsBelowZero_thenThrowException(){
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul", 100);
        int invalidMinimumStockLevel = -1;

        // Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> productService.setMinimumStockLevel(product, invalidMinimumStockLevel)
        );
        verify(productRepository, never()).save(product);
        
    }

    @Test
    void whenMinimumStockLevelIsEqualToZero_thenThrowException(){
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul", 100);
        int invalidMinimumStockLevel = 0;

        // Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> productService.setMinimumStockLevel(product, invalidMinimumStockLevel)
        );
        verify(productRepository, never()).save(product);
        
    }

    // ProductServiceTest.java
    @Test
    void whenSaveProductWithNegativeStock_thenThrowException() {
        // Arrange (Configuración)
        ProductRepository mockRepository = Mockito.mock(ProductRepository.class);
        ProductService productService = new ProductService(mockRepository);
        Product invalidProduct = new Product("Camiseta", -10); // Stock negativo

        // Act & Assert (Ejecutar y Verificar)
        assertThrows(IllegalArgumentException.class, () -> {
            productService.saveProduct(invalidProduct); // Debe lanzar excepción
        });

        // Verificar que NO se llamó a save (opcional)
        verify(mockRepository, never()).save(any());
    }
}
