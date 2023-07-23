package com.example.shopping.domain.entity;

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
    private Long review_seq;
    @Column(columnDefinition = "TEXT")
    private String content; //리뷰 내용
    private Integer rating; // 평점
    private String review_img; //리뷰 사진
}
