package com.example.shopping.payment.domain.entity;

import com.example.shopping.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentSeq;
    private int paymentAmount;
    private String paymentMethod;
    private String paymentStatus;
    private String cardType;
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;
}
