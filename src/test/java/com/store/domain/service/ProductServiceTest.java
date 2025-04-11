package com.store.domain.service;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    void increaseStock_ShouldIncrementExistingProductStock() {
        // Arrange (Preparar)
        // 1. Crear mock manualmente
        ProductRepository productRepository = mock(ProductRepository.class);
        
        // 2. Crear servicio inyectando el mock manualmente
        ProductService productService = new ProductService(productRepository);
        
        String productName = "Camiseta";
        int initialStock = 10;
        int incrementAmount = 5;
        Product existingProduct = new Product(productName, initialStock);
        
        // 3. Configurar comportamiento del mock
        when(productRepository.findByName(productName))
            .thenReturn(existingProduct);
        when(productRepository.save(any(Product.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // Act (Actuar)
        productService.increaseStock(productName, incrementAmount);

        // Assert (Verificar)
        verify(productRepository).findByName(productName);
        verify(productRepository).save(argThat(product -> 
            product.getName().equals(productName) && 
            product.getStock() == initialStock + incrementAmount));
    }
}