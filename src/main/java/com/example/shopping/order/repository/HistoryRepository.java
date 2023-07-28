package com.example.shopping.order.repository;

import com.example.shopping.order.domain.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<History> findById(Long historySeq);
}
