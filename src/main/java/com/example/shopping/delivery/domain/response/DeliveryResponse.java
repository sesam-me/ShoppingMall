package com.example.shopping.delivery.domain.response;

import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.payment.domain.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponse {
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
    private String deliveryCompanyContact;
    private String deliveryLocation;

    private Member member;
    private Payment payment;
//    private DeliveryCompany deliveryCompany;

    public DeliveryResponse(Delivery delivery) {
        this.deliverySeq = delivery.getDeliverySeq();
        this.deliveryDate = delivery.getDeliveryDate();
        this.deliveryStatus = delivery.getDeliveryStatus();
        this.recipientInformation = delivery.getRecipientInformation();
        this.deliveryMethod = delivery.getDeliveryMethod();
        this.deliveryFee = delivery.getDeliveryFee();
        this.recipientAddress = delivery.getRecipientAddress();
        this.recipientPhoneNumber = delivery.getRecipientPhoneNumber();
        this.member = Member.builder()
                .memberSeq(delivery.getMember().getMemberSeq())
                .address(delivery.getMember().getAddress())
                .id(delivery.getMember().getId())
                .username(delivery.getMember().getUsername())
                .registrationDate(delivery.getMember().getRegistrationDate())
                .point(delivery.getMember().getPoint())
                .grade(delivery.getMember().getGrade())
                .build();
        this.payment = Payment.builder()
                .paymentAmount(delivery.getPayment().getPaymentAmount())
                .build();
//        this.deliveryCompany = delivery.getDeliveryCompany();
    }

}
