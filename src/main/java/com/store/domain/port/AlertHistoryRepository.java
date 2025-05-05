package com.store.domain.port;

import java.util.List;

import com.store.domain.model.Alert;

public interface AlertHistoryRepository {
    List<Alert> findAll();
}
