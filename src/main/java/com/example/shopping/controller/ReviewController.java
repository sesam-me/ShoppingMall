package com.example.shopping.controller;

import com.example.shopping.domain.entity.Review;
import com.example.shopping.domain.request.ReviewRequest;
import com.example.shopping.service.ReviewService;
import lombok.Getter;
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
