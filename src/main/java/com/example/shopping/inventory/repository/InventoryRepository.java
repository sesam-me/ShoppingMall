package com.example.shopping.inventory.repository;

import com.example.shopping.inventory.domain.entity.Inventory;
import com.example.shopping.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findById(Long inventorySeq);

    Optional<Inventory> findByProduct(Product product);
}
