package com.store.domain.service;

public class ProductServiceTest {

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
