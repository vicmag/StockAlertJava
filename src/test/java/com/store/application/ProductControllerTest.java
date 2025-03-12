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
}