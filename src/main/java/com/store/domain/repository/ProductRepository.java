package com.store.domain.repository;

import com.store.domain.model.Product;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(String id);
}