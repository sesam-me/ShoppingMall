package com.example.shopping.delivery.domain.entity;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.payment.domain.entity.Payment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliverySeq;
    private LocalDateTime deliveryDate;
    private String deliveryStatus;
    private String recipientInformation;
    private String deliveryMethod;
    private Integer deliveryFee;
    private String recipientAddress;
    private String recipientPhoneNumber;
    private String deliveryFeeCondition;
    private String deliveryCompanySeq;
    private String deliveryContact;
    private String deliveryLocation;

//    private Long memberSeq;
//    private Long paymentSeq;
//    private Long deliveryCompanySeq;
//    private Long orderSeq;

    @ManyToOne
    @JoinColumn(name = "memberSeq") //join을 했을 때 기본키인 memberSeq가 기준이 됨. 아마 없어도 알아서 될 듯..?
    private Member member;
    @ManyToOne
    @JoinColumn(name = "paymentSeq")
    private Payment payment;
}
