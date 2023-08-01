package com.example.shopping.review.domain.entity;

import com.example.shopping.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewMemberSeq;

    @ManyToOne
    @JoinColumn(name = "review_seq")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;
}
