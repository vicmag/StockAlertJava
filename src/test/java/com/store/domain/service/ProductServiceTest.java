package com.store.domain.service;

import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void incrementStock_ValidIncrement_ShouldUpdateProductStock(){
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        String productName = "Camiseta Azul";
        int initialStock = 10;
        int increment = 5;
        Product product = new Product(productName, initialStock);        
        ProductService productService = new ProductService(productRepository);
        //doReturn(product).when(productRepository).findByName(productName);

        when(productRepository.findByName(productName)).thenReturn(product);
        
        // Act
        productService.incrementStock(productName, increment);
        
        // Assert
        assertEquals(initialStock + increment, product.getStock(), 
            "El stock debería incrementarse correctamente");
        verify(productRepository).findByName(productName);
        verify(productRepository).save(product);

    }

    @Test
    void incrementStock_ProductNotFound_ShouldThrowException() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        String productName = "Producto Inexistente";
        int increment = 5;
        ProductService productService = new ProductService(productRepository);
        doReturn(null).when(productRepository).findByName(any());
        //doThrow(IllegalArgumentException.class).when(productRepository).findByName(productName);
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> productService.incrementStock(productName, increment)
        );
        
        assertEquals("Producto no encontrado", exception.getMessage());
        verify(productRepository).findByName(productName);
        verify(productRepository, never()).save(any(Product.class));
    }


}

