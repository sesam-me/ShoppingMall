package com.example.shopping.member.repository;

import com.example.shopping.member.domain.entity.LoginHistory;
import com.example.shopping.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRecordRepository extends JpaRepository<LoginHistory, Long> {
    List<LoginHistory> findByMember(Member member);
}
