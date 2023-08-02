package com.example.shopping.product.repository;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.product.domain.entity.Interest;
import com.example.shopping.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findById(Long interestSeq);

    Interest findByMember(Member member);
    Interest findByProductAndMember(Product product, Member member);
    Interest findByProductProductSeqAndMemberMemberSeq(Long productSeq, Long memberSeq);
}
