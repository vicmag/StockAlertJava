package com.store.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import com.store.domain.port.ProductRepository;
import com.store.domain.model.Product;



public class ProductServiceTest {
    @Test    
    void cuandoElStockSeIncrementaDeArticuloExistente_entoncesSeDebeActualizarElStockDelArticulo(){
        // Arrange (Configuración)
        // 1. Mock
        ProductRepository productRepository = mock(ProductRepository.class);

        // 2. Servicio con la inyección del mock
        ProductService productService = new ProductService(productRepository);

        // 3. Producto
        String productName = "Camiseta";
        int initialStock = 10;
        int increment = 5;

        Product product = new Product(productName, initialStock);

        // 4. Comportamiento del mock
        when(productRepository.findByName(productName)).thenReturn(product);
        when(productRepository.save(product)).thenAnswer(invocation -> invocation.getArgument(0));
        // Equivalente a la línea de arriba - when(productRepository.save(product)).thenReturn(product);

        
        // Act (Ejecución)
        productService.increaseStock(productName, increment);

        
        // Assert (Validación)
        verify(productRepository).findByName(productName);
        verify(productRepository).save(product);
        assertEquals(initialStock + increment, product.getStock());

    }

    @Test    
    void cuandoElStockSeIncrementaDeArticuloNoExistente_entoncesSeDebeActualizarElStockDelArticulo(){
        // Arrange (Configuración)
        // 1. Mock
        ProductRepository productRepository = mock(ProductRepository.class);

        // 2. Servicio con la inyección del mock
        ProductService productService = new ProductService(productRepository);

        // 3. Producto
        String productName = "Camiseta";
        int increment = 5;
        String mensaje = "Producto inexistente";

        // 4. Comportamiento del mock
        when(productRepository.findByName(productName)).thenReturn(null);

        // Act (Ejecución) & Assert (Validación)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> productService.increaseStock(productName, increment));

        assertEquals(mensaje, exception.getMessage());

        verify(productRepository).findByName(productName);
        verify(productRepository, never()).save(any());

    }
}
