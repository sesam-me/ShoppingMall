package com.example.shopping.repository;

import com.example.shopping.domain.entity.Order;
import com.example.shopping.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long order_seq);
}
