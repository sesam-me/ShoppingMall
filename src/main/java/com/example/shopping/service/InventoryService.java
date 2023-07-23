package com.example.shopping.service;

import com.example.shopping.domain.entity.Inventory;
import com.example.shopping.domain.request.InventoryRequest;
import com.example.shopping.domain.request.InventoryUpdateRequest;
import com.example.shopping.domain.response.InventoryResponse;
import com.example.shopping.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }

    public void saveInventory(InventoryRequest inventoryRequest) {
        inventoryRepository.save(inventoryRequest.toEntity());
    }

    public void deleteInventory(Long inventory_seq) {
        inventoryRepository.deleteById(inventory_seq);
    }

    public InventoryResponse updateInventory(Long inventory_seq, InventoryUpdateRequest inventoryUpdateRequest) {
        Inventory inventory = findById(inventory_seq);
        inventory.update(inventoryUpdateRequest.getCount(), inventoryUpdateRequest.getSales(), inventoryUpdateRequest.getWaste());
        return new InventoryResponse(inventory);
    }

    private Inventory findById(Long inventorySeq) {
        return inventoryRepository.findById(inventorySeq).orElseThrow(() -> new RuntimeException());
    }
}
