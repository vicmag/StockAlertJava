package com.store.domain.service;

import org.junit.jupiter.api.Test;

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
        when(productRepository.save(product)).thenReturn(invocation -> ivoncation.getArgument(0));

        
        // Act (Ejecución)
        productService.increaseStock(productName, increment);

        
        //Assert (Validación)
        verify(productRepository).findByName(productName);
        verify(productRepository).save(product);
        assertEquals(initialStock + increment, product.getStock());


    }
}
