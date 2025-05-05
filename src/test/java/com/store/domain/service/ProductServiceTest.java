
package com.store.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import com.store.domain.model.Alert; // Ensure this import matches the package where Alert is defined
import com.store.domain.port.AlertHistoryRepository; // Ensure this matches the package where AlertHistoryRepository is defined

import org.junit.jupiter.api.Test;

import com.store.domain.model.Product;
import com.store.domain.port.ProductRepository;
import com.store.domain.port.AlertNotifier;
import java.util.Arrays;

public class ProductServiceTest {
    @Test
    void whenSetMinimumStockLevel_thenLevelIsSaved(){
        //Arrange (configuración)
        ProductRepository productRepository = mock(ProductRepository.class);
        AlertNotifier alertNotifier = mock(AlertNotifier.class);
        ProductService productService = new ProductService(productRepository, alertNotifier);
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
        AlertNotifier alertNotifier = mock(AlertNotifier.class);
        ProductService productService = new ProductService(productRepository, alertNotifier);
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
        AlertNotifier alertNotifier = mock(AlertNotifier.class);
        ProductService productService = new ProductService(productRepository, alertNotifier);
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

    @Test
    //El sistema debe permitir incrementar el stock de un producto existente
    //Happy path
    void cuadoIncrementoElInventario_entoncesElValorActualizadoSeAlmacena(){
        //Arrange
        int initialStock = 10;
        int increment = 5;
        String productName = "Camiseta Azul";
        Product product = new Product(productName);
        product.setStock(initialStock);

        ProductRepository productRepository = mock(ProductRepository.class);
        AlertNotifier alertNotifier = mock(AlertNotifier.class);
        ProductService productService = new ProductService(productRepository, alertNotifier);
        
        when(productRepository.findByName(any())).thenReturn(product);
        
        //Act
        productService.incrementStock(productName, increment);

        //Assert
        assertEquals(initialStock + increment, product.getStock());
        verify(productRepository).findByName(productName);
        verify(productRepository).save(product);

    }

    @Test
    //El sistema debe permitir incrementar el stock de un producto existente
    //Flujo de excepción
    void cuadoIncrementoElInventario_yElProductoNoSeEncuentra_entoncesSeLanzaExcepcion(){
        //Arrange
        int initialStock = 10;
        int increment = 5;
        String productName = "Camiseta Azul";
        Product product = new Product(productName);
        product.setStock(initialStock);

        ProductRepository productRepository = mock(ProductRepository.class);
        AlertNotifier alertNotifier = mock(AlertNotifier.class);
        ProductService productService = new ProductService(productRepository, alertNotifier);
        
        //when(productRepository.findByName(productName)).thenReturn(null);

        doReturn(null).when(productRepository).findByName(any());
        
        
        //Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> productService.incrementStock(productName, increment));

        assertEquals("El producto no existe.", exception.getMessage());
        verify(productRepository).findByName(productName);
        verify(productRepository, never()).save(product);
    }

    @Test
    // Escenario 3: Historial de Alertas
    // Dado: Alertas generadas para varios productos
    // Cuando: Administrador accede al historial
    // Entonces: El sistema muestra lista con fecha, producto y nivel de stock
    void cuandoConsultoHistorialAlertas_entoncesMuestraRegistrosCompletos() {
        // Arrange
        AlertHistoryRepository alertRepo = mock(AlertHistoryRepository.class);
        Product camiseta = new Product("Camiseta Azul");
        Product zapatos = new Product("Zapatos Deportivos");
        
        // Configurar mock para devolver alertas de ejemplo
        List<Alert> mockAlerts = Arrays.asList(
            new Alert(camiseta, 5, 10, LocalDateTime.now().minusDays(1)),
            new Alert(zapatos, 3, 8, LocalDateTime.now())
        );
        when(alertRepo.findAll()).thenReturn(mockAlerts);

        AlertService alertService = new AlertService(alertRepo);

        // Act
        List<Alert> historial = alertService.getAlertHistory();

        // Assert
        assertEquals(2, historial.size());
        assertEquals("Camiseta Azul", historial.get(0).getProductName());
        assertEquals(5, historial.get(0).getCurrentStock());
        verify(alertRepo).findAll();
    }

}
