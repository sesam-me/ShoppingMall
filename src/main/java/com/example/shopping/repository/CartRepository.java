package com.example.shopping.repository;

import com.example.shopping.domain.entity.Cart;
import com.example.shopping.domain.entity.InventoryAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findById(Long cart_seq);
}
