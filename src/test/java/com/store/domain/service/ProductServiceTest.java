package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    @Test
    void whenSetMinimumStockLevel_thenLevelIsSaved(){
        //Arrage
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul", 10);
        int newMinimumSotckLevel = 15;

        //Act
        productService.setMinimumStockLevel(product, newMinimumSotckLevel);

        //Assert
        verify(productRepository).save(product);
        assertEquals(newMinimumSotckLevel,  product.getMinimumStockLevel());

    }

    @Test
    void whenSetMinimumStockLevelWithInvalidValue_thenThrowException(){        
        //Arrage
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul", 10);
        int invalidMinimumSotckLevel = -1;
        

        //Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> productService.setMinimumStockLevel(product, invalidMinimumSotckLevel));
        
        assertEquals("El nivel mínimo debe ser mayor a cero.", exception.getMessage());
        verify(productRepository, never()).save(product);
                
    }

    @Test
    void whenSetMinimumStockLevelWithZeroValue_thenThrowException(){        
        //Arrage
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul", 10);
        int invalidMinimumSotckLevel = 0;
        

        //Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> productService.setMinimumStockLevel(product, invalidMinimumSotckLevel));
        
        assertEquals("El nivel mínimo debe ser mayor a cero.", exception.getMessage());
        verify(productRepository, never()).save(product);
                
    }

    // ProductServiceTest.java
    @Test
    void whenSaveProductWithNegativeStock_thenThrowException() {
        // Arrage (Configuración)
        ProductRepository mockRepository = Mockito.mock(ProductRepository.class);
        ProductService productService = new ProductService(mockRepository);
        Product invalidProduct = new Product("Camiseta", -10); // Stock negativo

        // Ejecutar y Verificar
        assertThrows(IllegalArgumentException.class, () -> {
            productService.saveProduct(invalidProduct); // Debe lanzar excepción
        });

        // Verificar que NO se llamó a save (opcional)
        verify(mockRepository, never()).save(any());
    }
  
    
}
