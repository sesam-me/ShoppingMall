package com.example.shopping.controller;

import com.example.shopping.domain.entity.InventoryAlert;
import com.example.shopping.domain.request.InventoryAlertRequest;
import com.example.shopping.service.InventoryAlterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alerts")
public class InventoryAlertController {
    private final InventoryAlterService inventoryAlterService;

    @GetMapping
    public List<InventoryAlert> findAllAlert() {
        return inventoryAlterService.findAllAlert();
    }

    @PostMapping
    public void saveAlert(@RequestBody InventoryAlertRequest inventoryAlertRequest) {
        inventoryAlterService.saveAlert(inventoryAlertRequest);
    }

    @DeleteMapping("{alert_seq}")
    public void deleteAlert(@PathVariable("alert_seq") Long alert_seq) {
        inventoryAlterService.deleteAlert(alert_seq);
    }
}
