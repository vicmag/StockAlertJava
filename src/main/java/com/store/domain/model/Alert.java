package com.store.domain.model;

import java.time.LocalDateTime;

public class Alert {
    private final Product product;
    private final int currentStock;
    private final int minimumStockLevel;
    private final LocalDateTime timestamp;

    public Alert(Product product, int currentStock, int minimumStockLevel, LocalDateTime timestamp) {
        this.product = product;
        this.currentStock = currentStock;
        this.minimumStockLevel = minimumStockLevel;
        this.timestamp = timestamp;
    }

    public String getProductName() {
        return product.getName();
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
