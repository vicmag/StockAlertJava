package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductServiceTest {
    @Test
    void whenSetMinimuStockLevel_LevelIsSaved(){
        //Arrage
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int minimumStockLevel = 15;

        //Act
        productService.setMinimumStockLevel(product, minimumStockLevel);

        //Assert
        verify(productRepository).save(product);
        assertEquals(minimumStockLevel, product.getMinimumStockLevel());    
    }
    
}
