package com.example.shopping.member.repository;

import com.example.shopping.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByIdAndPassword(String id, String pass);
    Member findById(String id);
}
