package com.example.shopping.review.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="qas")
public class QuestionAndAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qa_seq;
    private String q_title; //질문 제목
    @Column(columnDefinition = "TEXT")
    private String q_content; //질문 내용
    @Column(columnDefinition = "TEXT")
    private String answer; //답변

}
