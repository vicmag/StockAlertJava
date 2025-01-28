package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class LowStockAlertServiceTest {

    @Test
    public void testCheckLowStock_WhenStockIsLow_ShouldReturnTrue() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        LowStockAlertService service = new LowStockAlertService(productRepository);

        Product product = new Product("1", "Product A", 5, 10); // 5 is current stock, 10 is threshold
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        // Act
        boolean isLowStock = service.checkLowStock("1");

        // Assert
        assertTrue(isLowStock);
    }

    @Test
    public void testCheckLowStock_WhenStockIsNotLow_ShouldReturnFalse() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        LowStockAlertService service = new LowStockAlertService(productRepository);

        Product product = new Product("1", "Product A", 15, 10); // 15 is current stock, 10 is threshold
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        // Act
        boolean isLowStock = service.checkLowStock("1");

        // Assert
        assertFalse(isLowStock);
    }
}