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
@Table(name = "qas")
public class QuestionAndAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qaSeq;

    private String title; //질문 제목
    @Column(columnDefinition = "TEXT")
    private String content; //질문 내용
    @Column(columnDefinition = "TEXT")
    private String answer; //답변

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private Product product;
}
