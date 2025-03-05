 // ProductService.java (Servicio de Dominio - Actualizado)
 package com.store.domain.service;

 import com.store.domain.model.Product;
 import com.store.domain.port.ProductRepository;
 import com.store.domain.port.AlertNotifier;
 
 public class ProductService {
     private final ProductRepository productRepository;
     private final AlertNotifier alertNotifier;
 
     public ProductService(ProductRepository productRepository, AlertNotifier alertNotifier) {
         this.productRepository = productRepository;
         this.alertNotifier = alertNotifier;
     }
 
     public void setMinimumStockLevel(Product product, int minimumStockLevel) {
         validateMinimumStockLevel(minimumStockLevel);
         product.setMinimumStockLevel(minimumStockLevel);
         productRepository.save(product);
     }
 
     public void checkStockLevel(Product product) {
         if (product.getStock() <= product.getMinimumStockLevel()) {
             alertNotifier.notifyLowStock(product);
         }
     }
 
     private void validateMinimumStockLevel(int minimumStockLevel) {
         if (minimumStockLevel <= 0) {
             throw new IllegalArgumentException("El nivel mínimo de stock debe ser mayor que cero.");
         }
     }
 }
     