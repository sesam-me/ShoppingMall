package com.example.shopping.review.repository;

import com.example.shopping.review.domain.entity.QuestionAndAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionAndAnswerRepository extends JpaRepository<QuestionAndAnswer,Long> {
    Optional<QuestionAndAnswer> findById(Long qa_seq);
}
