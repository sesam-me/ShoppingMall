package com.example.shopping.member.repository;

import com.example.shopping.member.domain.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRecordRepository extends JpaRepository<LoginHistory, Long> {
}
