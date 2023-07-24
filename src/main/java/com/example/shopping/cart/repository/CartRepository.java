package com.example.shopping.cart.repository;

import com.example.shopping.cart.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findById(Long cart_seq);
}
