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

    @PostMapping("{productSeq}")
    public void saveInventory(@RequestBody InventoryRequest inventoryRequest, @PathVariable("productSeq") Long productSeq) {
        inventoryService.saveInventory(inventoryRequest, productSeq);
    }

    @DeleteMapping("{inventorySeq}")
    public void deleteInventory(@PathVariable("inventorySeq") Long inventorySeq) {
        inventoryService.deleteInventory(inventorySeq);
    }

    @PutMapping("{inventorySeq}")
    public InventoryResponse updateInventory(@PathVariable("inventorySeq") Long inventorySeq, @RequestBody InventoryUpdateRequest inventoryUpdateRequest) {
        return inventoryService.updateInventory(inventorySeq, inventoryUpdateRequest);
    }
}
