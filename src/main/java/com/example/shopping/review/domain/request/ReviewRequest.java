package com.example.shopping.review.domain.request;

import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.review.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String content;
    private Integer rating;
    private String reviewImg;

    public Review toEntity(Long productSeq) {
        return Review.builder()
                .content(content)
                .rating(rating)
                .reviewImg(reviewImg)
                .product(Product.builder().productSeq(productSeq).build())
                .build();
    }

}
