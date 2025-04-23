
package com.store.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;
import com.store.domain.port.AlertNotifier;

public class ProductServiceTest {
    @Test
    void whenSetMinimumStockLevel_thenLevelIsSaved(){
        //Arrange (configuración)
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int newMinimumStockLevel = 15;

        //Act (ejecución)
        productService.setMinimumStockLevel(product, newMinimumStockLevel);

        //Assert (validación)
        assertEquals(newMinimumStockLevel, product.getMinimumStockLevel());
        verify(productRepository).save(product);        

    }

    @Test
    void cuandoUmbralMinimoEsInvalido_entoncesSeLanzaExcepcion(){
        //Arrange (configuración)
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int invalidMinimumStockLevel = -1;

        //Act (ejecución) Assert (validación)
        assertThrows(
            IllegalArgumentException.class, 
            () -> productService.setMinimumStockLevel(product, invalidMinimumStockLevel)
            );
        verify(productRepository, never()).save(product);

    }

    @Test
    void cuandoUmbralMinimoEsCero_entoncesSeLanzaExcepcion(){
        //Arrange (configuración)
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product product = new Product("Camiseta Azul");
        int invalidMinimumStockLevel = 0;

        //Act (ejecución) Assert (validación)
        assertThrows(
            IllegalArgumentException.class, 
            () -> productService.setMinimumStockLevel(product, invalidMinimumStockLevel)
            );
        verify(productRepository, never()).save(product);

    }

    @Test
    void cuandoInventarioEstaDebajoDelUmbral_entoncesLanzoUnaAlerta(){
        //Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        AlertNotifier alertNotifier = mock(AlertNotifier.class);
        ProductService productService = new ProductService(productRepository, alertNotifier);
        Product product = new Product("Camiseta Azul");
        product.setStock(20);
        int minimumStockLevel = 10;
        productService.setMinimumStockLevel(product, minimumStockLevel);

        //Act
        product.setStock(5);
        productService.checkStockLevel(product);

        //Assert
        verify(alertNotifier).notifyLowStock(product);
    }
}
