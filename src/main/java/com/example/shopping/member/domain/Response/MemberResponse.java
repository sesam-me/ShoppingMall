package com.example.shopping.member.domain.Response;

import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.member.domain.entity.Grade;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.domain.entity.Point;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private Long memberSeq;
    private String id;
    private String password;
    private String username;
    private LocalDateTime registrationDate = LocalDateTime.now();
    private String address;
    private List<DeliveryDto> deliveries;
    private Point point;
    private Grade grade;


    public MemberResponse(Member member) {
        this.memberSeq = member.getMemberSeq();
        this.id = member.getId();
        this.password = member.getPassword();
        this.username = member.getUsername();
        this.registrationDate = member.getRegistrationDate();
        this.address = member.getAddress();
        this.deliveries = member.getDeliveries() != null ?
                member.getDeliveries().stream().map(DeliveryDto::new).toList() : new ArrayList<>();

        this.point = member.getPoint();

        this.grade = member.getGrade();
    }

    @Getter @AllArgsConstructor
    class DeliveryDto{
        private Long deliverSeq;
        private LocalDateTime deliveryDate;
        private String deliverStatus;
        private String recipientInformation;
        private String deliveryMethod;
        private Integer deliveryFee;
        private String recipientAddress;
        private String recipientPhoneNumber;
        private String deliveryFeeCondition;
        private String deliveryCompanyName;
        private String deliveryCompanyContact;
        private String deliveryLocation;

        private Long memberSeq;
        private Long paymentSeq;
//        private Long orderSeq;

        public DeliveryDto(Delivery delivery) {
            this.deliverSeq = delivery.getDeliverySeq();
            this.deliveryDate = delivery.getDeliveryDate();
            this.deliverStatus = delivery.getDeliveryStatus();
            this.recipientInformation = delivery.getRecipientInformation();
            this.deliveryMethod = delivery.getDeliveryMethod();
            this.deliveryFee = delivery.getDeliveryFee();
            this.recipientAddress = delivery.getRecipientAddress();
            this.recipientPhoneNumber = delivery.getRecipientPhoneNumber();
            this.deliveryFeeCondition = delivery.getDeliveryFeeCondition();
            this.deliveryCompanyName = delivery.getDeliveryCompanyName();
            this.deliveryCompanyContact = delivery.getDeliveryCompanyContact();
            this.deliveryLocation = delivery.getDeliveryLocation();

            this.memberSeq = delivery.getMember().getMemberSeq();
            this.paymentSeq = delivery.getPayment().getPaymentSeq();
//            this.orderSeq = delivery.getMember().getOderSeq;
        }
    }


}
