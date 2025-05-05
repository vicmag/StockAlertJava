package com.store.domain.service;

import java.util.List;

import com.store.domain.model.Alert;
import com.store.domain.port.AlertHistoryRepository;

public class AlertService {
    private final AlertHistoryRepository alertHistoryRepository;

    public AlertService(AlertHistoryRepository alertHistoryRepository) {
        this.alertHistoryRepository = alertHistoryRepository;
    }

    public List<Alert> getAlertHistory() {
        return alertHistoryRepository.findAll();
    }
}
