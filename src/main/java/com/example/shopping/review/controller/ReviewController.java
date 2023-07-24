package com.example.shopping.review.controller;

import com.example.shopping.review.domain.entity.Review;
import com.example.shopping.review.domain.request.ReviewRequest;
import com.example.shopping.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public List<Review> findAllReview() {
        return reviewService.findAllReview();
    }

    @PostMapping
    public void saveReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.saveReview(reviewRequest);
    }

    @DeleteMapping("{review_seq}")
    private void deleteReview(@PathVariable("review_seq") Long review_seq) {
        reviewService.deleteReview(review_seq);
    }
}
