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

    @PostMapping("{productSeq}")
    public void saveReview(@RequestBody ReviewRequest reviewRequest,
                           @PathVariable("productSeq") Long productSeq) {
        reviewService.saveReview(reviewRequest, productSeq);
    }

    @DeleteMapping("{reviewSeq}")
    private void deleteReview(@PathVariable("reviewSeq") Long reviewSeq) {
        reviewService.deleteReview(reviewSeq);
    }
}
