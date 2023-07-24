package com.example.shopping.inventory.controller;

import com.example.shopping.inventory.domain.entity.Inventory;
import com.example.shopping.inventory.domain.request.InventoryRequest;
import com.example.shopping.inventory.domain.request.InventoryUpdateRequest;
import com.example.shopping.inventory.domain.response.InventoryResponse;
import com.example.shopping.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public List<Inventory> findAllInventory() {
        return inventoryService.findAllInventory();
    }

    @PostMapping
    public void saveInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.saveInventory(inventoryRequest);
    }

    @DeleteMapping("{inventory_seq}")
    public void deleteInventory(@PathVariable("inventory_seq") Long inventory_seq) {
        inventoryService.deleteInventory(inventory_seq);
    }

    @PutMapping("{inventory_seq}")
    public InventoryResponse updateInventory(@PathVariable("inventory_seq") Long inventory_seq, @RequestBody InventoryUpdateRequest inventoryUpdateRequest) {
        return inventoryService.updateInventory(inventory_seq, inventoryUpdateRequest);
    }
}
