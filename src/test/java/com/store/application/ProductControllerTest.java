// ProductControllerTest.java (Prueba unitaria del controlador)
package com.store.application;

import com.store.domain.model.Product;
import com.store.domain.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void whenSetMinimumStockLevel_thenReturnOk() {
        // Arrange
        String productId = "1";
        int minimumStockLevel = 10;

        // Act
        ResponseEntity response = productController.setMinimumStockLevel(productId, minimumStockLevel);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService).setMinimumStockLevel(any(Product.class), eq(minimumStockLevel));
    }

    @Test
    void whenCheckStockLevel_thenReturnOk() {
        // Arrange
        String productId = "1";

        // Act
        ResponseEntity response = productController.checkStockLevel(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService).checkStockLevel(any(Product.class));
    }

    @Test
    void whenProductDoesNotExist_thenReturnNotFound() {
        // Arrange
        String productId = "999"; // ID de un producto que no existe
        int minimumStockLevel = 10;

        // Simular una excepción cuando el producto no existe
        doThrow(new IllegalArgumentException("Producto no encontrado"))
            .when(productService)
            .setMinimumStockLevel(any(Product.class), eq(minimumStockLevel));

        // Act
        ResponseEntity response = productController.setMinimumStockLevel(productId, minimumStockLevel);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}