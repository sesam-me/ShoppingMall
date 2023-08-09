package com.example.shopping.review.controller;

import com.example.shopping.common.RestResult;
import com.example.shopping.review.domain.entity.Review;
import com.example.shopping.review.domain.request.ReviewRequest;
import com.example.shopping.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@CrossOrigin("*")
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

    @PostMapping("/{reviewSeq}/like/{memberSeq}")
    public ResponseEntity<RestResult<Object>> saveLike(@PathVariable("reviewSeq") Long reviewSeq, @PathVariable("memberSeq") Long memberSeq) {
        return reviewService.saveLike(reviewSeq, memberSeq);
    }

    @DeleteMapping("/{reviewSeq}/like")
    public void deleteLike(@PathVariable("reviewSeq") Long reviewSeq, @RequestParam("memberSeq") Long memberSeq) {
        reviewService.deleteLike(memberSeq, reviewSeq);
    }

}


