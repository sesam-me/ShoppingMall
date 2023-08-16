package com.example.shopping.member.domain.Response;

import com.example.shopping.cart.domain.dto.CartDto;
import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.member.domain.entity.Grade;
import com.example.shopping.member.domain.entity.LoginHistory;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.domain.entity.Point;
import com.example.shopping.product.domain.entity.Interest;
import com.example.shopping.review.domain.entity.Review;
import lombok.*;

import java.time.LocalDate;
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
    private String phoneNum;
    private List<DeliveryDto> deliveries;
    private Point point;
    private Grade grade;
    private List<LoginHistory> loginHistory;
    private List<InterestsDto> interests;
    private List<CartDto> carts;



    public MemberResponse(Member member) {
        this.memberSeq = member.getMemberSeq();
        this.id = member.getId();
        this.password = member.getPassword();
        this.username = member.getUsername();
        this.registrationDate = member.getRegistrationDate();
        this.address = member.getAddress();
        this.phoneNum = member.getPhoneNum();
        this.deliveries = member.getDeliveries() != null ?
                member.getDeliveries().stream().map(DeliveryDto::new).toList() : new ArrayList<>();
        this.point = member.getPoint();
        this.grade = member.getGrade();
        this.loginHistory = member.getLoginHistory();
        this.interests = member.getInterests() != null ?
        member.getInterests().stream().map(InterestsDto::new).toList() :new ArrayList<>();
        this.carts = member.getCarts().stream().map(CartDto::new).toList();
    }



    @Getter @AllArgsConstructor
    class InterestsDto {
        private Long interestSeq;
        private LocalDate interestDate; //관심 표현한 날짜
        private Integer interestLike; // 관심 상태 좋아요인지 아닌지
        private Long productSeq;
        private String hname;
        private String ename;
        private String imgUrl;
        private String detailImgUrl;
        private LocalDate createAt;
        private String brand;
        private Integer price;
        private Review review;

        public InterestsDto(Interest interest) {
            this.interestSeq = interest.getInterestSeq();
            this.interestDate = interest.getInterestDate();
            this.interestLike = interest.getInterestLike();
            this.productSeq = interest.getProduct().getProductSeq();
            this.hname = interest.getProduct().getHname();
            this.ename = interest.getProduct().getEname();
            this.imgUrl = interest.getProduct().getImgUrl();
            this.detailImgUrl = interest.getProduct().getDetailImgUrl();
            this.createAt = interest.getProduct().getCreateAt();
            this.brand = interest.getProduct().getBrand();
            this.price = interest.getProduct().getPrice();
        }
    }

    @Getter @AllArgsConstructor
    class DeliveryDto{
        private Long deliverSeq;
        private LocalDateTime deliveryDate;
        private Integer deliverStatus;
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
        }
    }


}
