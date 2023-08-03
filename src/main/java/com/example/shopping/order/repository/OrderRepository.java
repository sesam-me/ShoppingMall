package com.example.shopping.order.repository;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.order.domain.response.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long orderSeq);

    List<Order> findAllByMember(Member member);
    Order findByMember(Member member);
}
