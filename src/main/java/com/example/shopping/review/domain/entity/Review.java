package com.example.shopping.review.domain.entity;

import com.example.shopping.product.domain.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewSeq;
    @Column(columnDefinition = "TEXT")
    private String content; //리뷰 내용
    private Integer rating; // 평점
    private String reviewImg; //리뷰 사진

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private Product product;
}
