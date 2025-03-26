package com.store.domain.service;

import static org.mockito.Mockito.mock;

import com.store.domain.port.ProductRepository; 
import com.store.domain.model.Product;

public class ProductServiceTest {
    void whenSetMinimumStockLevel_thenLevelIsSaved(){
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int newMinimumStockLevel = 15;

        // Act
        productService.setMinimumStockLevet(product, newMinimumStockLevel);

        // Assert        
        verify(productRepository).save(product);
        assertEquals(newMinimumStockLevel, product.getMinimumStockLevel());

    }
}
