package com.example.shopping.member.domain.Response;

import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.member.domain.entity.Member;
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
    private boolean gender;
    private LocalDateTime registrationDate = LocalDateTime.now();
    private String address;
    private List<DeliveryDto> deliveries;


    public MemberResponse(Member member) {
        this.memberSeq = member.getMemberSeq();
        this.id = member.getId();
        this.password = member.getPassword();
        this.username = member.getUsername();
        this.gender = member.isGender();
        this.registrationDate = member.getRegistrationDate();
        this.address = member.getAddress();
        this.deliveries = member.getDeliveries() != null ?
                member.getDeliveries().stream().map(DeliveryDto::new).toList() : new ArrayList<>();

        ;
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

        private Long memberSeq;
        private Long paymentSeq;
        private Long deliveryCompanySeq;
        private Long orderSeq;

        public DeliveryDto(Delivery delivery) {
            this.deliverSeq = delivery.getDeliverSeq();
            this.deliveryDate = delivery.getDeliveryDate();
            this.deliverStatus = delivery.getDeliverStatus();
            this.recipientInformation = delivery.getRecipientInformation();
            this.deliveryMethod = delivery.getDeliveryMethod();
            this.deliveryFee = delivery.getDeliveryFee();
            this.recipientAddress = delivery.getRecipientAddress();
            this.recipientPhoneNumber = delivery.getRecipientPhoneNumber();
            this.memberSeq = delivery.getMember().getMemberSeq();
            this.paymentSeq = delivery.getMember().getMemberSeq();
            this.deliveryCompanySeq = delivery.getMember().getMemberSeq();
//            this.orderSeq = delivery.getMember().getOderSeq;
        }
    }


}
