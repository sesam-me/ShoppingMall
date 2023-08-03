package com.example.shopping.delivery.repository;

import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findAllByMember(Member member);
}
