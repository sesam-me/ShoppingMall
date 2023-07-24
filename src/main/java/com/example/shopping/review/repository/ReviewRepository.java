package com.example.shopping.review.repository;

import com.example.shopping.review.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository <Review,Long> {
    Optional<Review> findById(Long review_seq);
}
