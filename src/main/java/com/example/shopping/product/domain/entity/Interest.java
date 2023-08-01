package com.example.shopping.product.domain.entity;

import com.example.shopping.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "interests")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestSeq;
    private LocalDate interestDate; //관심 표현한 날짜
    private Integer interestLike; // 관심 상태 좋아요인지 아닌지

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;


}
