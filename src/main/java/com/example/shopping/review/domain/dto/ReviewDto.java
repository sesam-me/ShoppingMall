package com.example.shopping.review.domain.dto;

import com.example.shopping.review.domain.entity.Review;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewDto {
    private Long reviewSeq;
    private String content; //리뷰 내용
    private Integer rating; // 평점
    private String reviewImg; //리뷰 사진
    private Long productSeq;

    public ReviewDto(Review review) {
        this.reviewSeq = review.getReviewSeq();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.reviewImg = review.getReviewImg();
        this.productSeq = review.getProduct().getProductSeq();
    }
}
