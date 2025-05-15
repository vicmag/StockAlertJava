package com.store.domain.port;

import com.store.domain.model.Product;

public interface ProductRepository {
    Product findByName(String productName);
    Product save(Product product);

}
