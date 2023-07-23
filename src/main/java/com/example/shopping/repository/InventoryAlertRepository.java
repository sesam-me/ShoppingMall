package com.example.shopping.repository;

import com.example.shopping.domain.entity.InventoryAlert;
import com.example.shopping.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryAlertRepository extends JpaRepository<InventoryAlert,Long> {
    Optional<InventoryAlert> findById(Long alert_seq);
}
