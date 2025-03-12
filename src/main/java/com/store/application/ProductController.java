// ProductController.java (Refactorizado)
package com.store.application;

import com.store.domain.model.Product;
import com.store.domain.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/{id}/minimum-stock")
    public ResponseEntity setMinimumStockLevel(
            @PathVariable String id,
            @RequestParam int minimumStockLevel) {
        Product product = getProductById(id); // Extracción de lógica repetitiva
        productService.setMinimumStockLevel(product, minimumStockLevel);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/check-stock")
    public ResponseEntity checkStockLevel(@PathVariable String id) {
        Product product = getProductById(id); // Extracción de lógica repetitiva
        productService.checkStockLevel(product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private Product getProductById(String id) {
        return new Product(id, 0); // Simulación de un producto existente
    }
}