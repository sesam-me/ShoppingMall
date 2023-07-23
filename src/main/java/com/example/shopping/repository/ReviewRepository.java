package com.example.shopping.repository;

import com.example.shopping.domain.entity.Product;
import com.example.shopping.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository <Review,Long> {
    Optional<Review> findById(Long review_seq);
}
