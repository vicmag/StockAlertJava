package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    void whenSetMinimumStockLevel_thenLevelIsSaved() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul", 20);
        int newMinimumStockLevel = 15;

        // Act
        productService.setMinimumStockLevel(product, newMinimumStockLevel);

        // Assert
        verify(productRepository).save(product);
        assertEquals(newMinimumStockLevel, product.getMinimumStockLevel());
    }

    @Test
    void whenSetMinimumStockLevelWithInvalidValue_thenThrowException() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul", 20);
        int invalidMinimumStockLevel = 0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> productService.setMinimumStockLevel(product, invalidMinimumStockLevel)
        );
        assertEquals("El nivel mínimo de stock debe ser mayor que cero.", exception.getMessage());
        verify(productRepository, never()).save(product);
    }
}