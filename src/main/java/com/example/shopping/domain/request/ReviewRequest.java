package com.example.shopping.domain.request;

import com.example.shopping.domain.entity.Review;
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
    private String review_img;

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .rating(rating)
                .review_img(review_img)
                .build();
    }

}
