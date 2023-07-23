package com.example.shopping.repository;

import com.example.shopping.domain.entity.Product;
import com.example.shopping.domain.entity.QuestionAndAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionAndAnswerRepository extends JpaRepository<QuestionAndAnswer,Long> {
    Optional<QuestionAndAnswer> findById(Long qa_seq);
}
