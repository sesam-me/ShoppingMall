package com.example.shopping.review.service;

import com.example.shopping.review.domain.entity.Review;
import com.example.shopping.review.domain.request.ReviewRequest;
import com.example.shopping.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> findAllReview() {
        return reviewRepository.findAll();
    }

    public void saveReview(ReviewRequest reviewRequest) {
        reviewRepository.save(reviewRequest.toEntity());
    }

    public void deleteReview(Long review_seq) {
        reviewRepository.deleteById(review_seq);
    }
}
