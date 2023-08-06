package com.example.shopping.order.domain.request;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String orderNum; // 주문번호
    private LocalDateTime orderDate; //주문일자
    private int paymentAmount; // 결재금액
    private String paymentMethod; // 결재방법
    private String paymentStatus; // 결재상태
    private String cardType; //카드타입
    private LocalDateTime paymentDate; //결재일
    private String recipientAddress; //배송지
    private Long memberSeq;
    private Long productSeq;


    public Order toEntity(Long productSeq, Long memberSeq) {
        return Order.builder()
                .orderNum(orderNum)
                .orderDate(orderDate)
                .member(Member.builder().memberSeq(memberSeq).build())
                .product(Product.builder().productSeq(productSeq).build())
                .build();
    }
}
