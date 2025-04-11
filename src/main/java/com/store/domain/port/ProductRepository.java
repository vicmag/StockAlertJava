// ProductRepository.java (Puerto de Dominio)
package com.store.domain.port;

import com.store.domain.model.Product;

public interface ProductRepository {
    Product save(Product product);
    Product findByName(String name);
}