package com.store.domain.model;

public class Product {
    private String id;
    private String name;
    private int currentStock;
    private int lowStockThreshold;

    public Product(String id, String name, int currentStock, int lowStockThreshold) {
        this.id = id;
        this.name = name;
        this.currentStock = currentStock;
        this.lowStockThreshold = lowStockThreshold;
    }

    public String getId() {
        return id;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }
}