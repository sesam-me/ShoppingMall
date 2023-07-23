package com.example.shopping.repository;

import com.example.shopping.domain.entity.Interest;
import com.example.shopping.domain.entity.InventoryAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findById(Long interest_seq);
}
