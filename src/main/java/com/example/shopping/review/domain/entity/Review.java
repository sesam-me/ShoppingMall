package com.example.shopping.review.domain.entity;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.product.domain.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private Integer heart;

    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewMember> reviewMembers;
}
