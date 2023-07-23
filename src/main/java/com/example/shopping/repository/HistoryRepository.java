package com.example.shopping.repository;

import com.example.shopping.domain.entity.History;
import com.example.shopping.domain.entity.InventoryAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<History> findById(Long history_seq);
}
