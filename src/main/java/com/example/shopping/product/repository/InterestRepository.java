package com.example.shopping.product.repository;

import com.example.shopping.product.domain.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findById(Long interestSeq);
}
