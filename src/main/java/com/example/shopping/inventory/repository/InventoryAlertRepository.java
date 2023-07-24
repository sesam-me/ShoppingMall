package com.example.shopping.inventory.repository;

import com.example.shopping.inventory.domain.entity.InventoryAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryAlertRepository extends JpaRepository<InventoryAlert,Long> {
    Optional<InventoryAlert> findById(Long alert_seq);
}
