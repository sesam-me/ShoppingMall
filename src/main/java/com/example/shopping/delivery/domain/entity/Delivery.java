package com.example.shopping.delivery.domain.entity;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.payment.domain.entity.Payment;
import com.example.shopping.product.domain.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliverySeq;
    private LocalDateTime deliveryDate;
    private Integer deliveryStatus;
    private String recipientInformation;
    private String deliveryMethod;
    private Integer deliveryFee;
    private String recipientAddress;
    private String recipientPhoneNumber;
    private String deliveryFeeCondition;
    private String deliveryCompanyName;
    private String deliveryCompanyContact;
    private String deliveryLocation;

//    private Long memberSeq;
//    private Long paymentSeq;
//    private Long orderSeq;

    @ManyToOne
    @JoinColumn(name = "memberSeq") //join을 했을 때 기본키인 memberSeq가 기준이 됨. 아마 없어도 알아서 될 듯..?
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "paymentSeq")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "productSeq")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderSeq")
    private Order order;
}
