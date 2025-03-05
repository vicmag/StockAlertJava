// AlertNotifier.java (Nueva Interfaz para Notificaciones)
package com.store.domain.port;

import com.store.domain.model.Product;

public interface AlertNotifier {
    void notifyLowStock(Product product);
}