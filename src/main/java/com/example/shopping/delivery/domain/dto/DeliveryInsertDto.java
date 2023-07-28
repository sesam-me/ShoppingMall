package com.example.shopping.delivery.domain.dto;

import com.example.shopping.member.domain.entity.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DeliveryInsertDto {
    private LocalDateTime deliveryDate;
    private String deliverStatus;
    private String recipientInformation;
    private String deliveryMethod;
    private Integer deliveryFee;
    private String recipientAddress;
    private String recipientPhoneNumber;

    private Long member_seq;
    private Long payment_seq;
    private Long order_seq;

    // member_seq를 가져올 수 없어서 대신, 객체를 가져온거임
    @ManyToOne
    @JoinColumn(name = "id") // Member에 memberId가 없지만, join을 했을 때 기본키인 id가 memberId가 됨.
    private Member members;



//    {
//        "LocalDateTime" : "2023-07-07",
//            "deliverStatus" : "결제완료",
//        "recipientInformation" : "이동명",
//        "deliveryMethod" : "택배",
//        "deliveryFee" : 30_000,
//        "recipientAddress" : "경기도 수원시",
//        "recipientPhoneNumber" : "010"
//
//    }
}
