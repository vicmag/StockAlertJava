package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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
    
}
