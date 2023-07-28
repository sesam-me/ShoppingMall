package com.example.shopping.member.repository;

import com.example.shopping.member.domain.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
