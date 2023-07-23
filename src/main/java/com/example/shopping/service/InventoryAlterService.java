package com.example.shopping.service;

import com.example.shopping.domain.entity.InventoryAlert;
import com.example.shopping.domain.request.InventoryAlertRequest;
import com.example.shopping.repository.InventoryAlertRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryAlterService {
    private final InventoryAlertRepository inventoryAlertRepository;

    public List<InventoryAlert> findAllAlert() {
        return inventoryAlertRepository.findAll();
    }

    public void saveAlert(InventoryAlertRequest inventoryAlertRequest) {
        inventoryAlertRepository.save(inventoryAlertRequest.toEntity());
    }

    public void deleteAlert(Long alert_seq) {
        inventoryAlertRepository.deleteById(alert_seq);
    }
}
