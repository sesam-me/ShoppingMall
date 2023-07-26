package com.example.shopping.inventory.service;

import com.example.shopping.inventory.domain.entity.Inventory;
import com.example.shopping.inventory.domain.request.InventoryRequest;
import com.example.shopping.inventory.domain.request.InventoryUpdateRequest;
import com.example.shopping.inventory.domain.response.InventoryResponse;
import com.example.shopping.inventory.repository.InventoryRepository;
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

    public void saveInventory(InventoryRequest inventoryRequest, Long productSeq) {
        inventoryRepository.save(inventoryRequest.toEntity(productSeq));
    }

    public void deleteInventory(Long inventorySeq) {
        inventoryRepository.deleteById(inventorySeq);
    }

    public InventoryResponse updateInventory(Long inventorySeq, InventoryUpdateRequest inventoryUpdateRequest) {
        Inventory inventory = findById(inventorySeq);
        inventory.update(inventoryUpdateRequest.getCount(), inventoryUpdateRequest.getSales(), inventoryUpdateRequest.getWaste());
        return new InventoryResponse(inventory);
    }

    private Inventory findById(Long inventorySeq) {
        return inventoryRepository.findById(inventorySeq).orElseThrow(() -> new RuntimeException());
    }
}
