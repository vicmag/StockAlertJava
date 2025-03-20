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
        Product product = new Product("Camiseta Azul");
        int newMinimumStockLevel = 15;

        // Act
        productService.setMinimumStockLevel(product, newMinimumStockLevel);

        // Assert
        verify(productRepository).save(product);
        assertEquals(newMinimumStockLevel, product.getMinimumStockLevel());
    }

    @Test
    void whenSetMinimumStockLevel_equaltozero_thenError() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int invalidMinimumStockLevel = 0;

    
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, () -> productService.setMinimumStockLevel(product, invalidMinimumStockLevel));    
        assertEquals("El nivel mínimo deber ser mayor a cero.", exception.getMessage());
        verify(productRepository,never()).save(product);
    }

    @Test
    void whenStockIsBelowMinimumLevel_thenAlertTriggered() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        AlertNotifier alertNotifier = mock(AlertNotifier.class);

        ProductService productService = new ProductService(productRepository, alertNotifier);

        Product product = new Product("Camiseta Azul");
        int newMinimumStockLevel = 10;
        productService.setMinimumStockLevel(product, newMinimumStockLevel);

        //Act
        product.setStock(5); //Reducir el stock a un nivel menor que el mínimo que es 10
        
        //Assert
        verify(alertNotifier).notifyLowStock(product);
            
    }
}