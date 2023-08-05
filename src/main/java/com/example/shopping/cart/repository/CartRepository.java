package com.example.shopping.cart.repository;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {


}
