package com.example.shopping.cart.repository;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMember_MemberSeqAndProduct_ProductSeq(Long memberSeq, Long productSeq);
}
