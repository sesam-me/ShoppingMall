package com.example.shopping.delivery.service;

import com.example.shopping.delivery.domain.dto.DeliveryInsertDto;
import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.delivery.domain.response.DeliveryResponse;
import com.example.shopping.delivery.repository.DeliveryRepository;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.repository.MemberRepository;
import com.example.shopping.payment.domain.entity.Payment;
import com.example.shopping.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;

    public void deliveryInsert(DeliveryInsertDto deliveryInsertDto){
        Optional<Member> findByMemberSeq = memberRepository.findById(deliveryInsertDto.getMember_seq());
        Optional<Payment> findByPaymentSeq = paymentRepository.findById(deliveryInsertDto.getPayment_seq());

        System.out.println(findByMemberSeq);
        System.out.println(findByPaymentSeq);


        Delivery delivery = Delivery.builder()
                .deliveryDate(deliveryInsertDto.getDeliveryDate())
                .deliveryStatus(deliveryInsertDto.getDeliverStatus())
                .recipientInformation(deliveryInsertDto.getRecipientInformation())
                .deliveryMethod(deliveryInsertDto.getDeliveryMethod())
                .deliveryFee(deliveryInsertDto.getDeliveryFee())
                .recipientAddress(deliveryInsertDto.getRecipientAddress())
                .recipientPhoneNumber(deliveryInsertDto.getRecipientPhoneNumber())
                .deliveryFeeCondition(deliveryInsertDto.getDeliveryFeeCondition())
                .deliveryCompanyName(deliveryInsertDto.getDeliveryCompanyName())
                .deliveryCompanyContact(deliveryInsertDto.getDeliveryCompanyContact())
                .deliveryLocation(deliveryInsertDto.getDeliveryLocation())
                .member(findByMemberSeq.get())  // 해당하는 Member_seq를 찾아서 관련된 다른 값도 가져옴.
//                .member(Member.builder().memberSeq(deliveryInsertDto.getMember_seq()).build()) 이건 Member_seq만 가져와서 넣는 것
                .payment(findByPaymentSeq.get())
                .build();
        deliveryRepository.save(delivery);
    }

    public List<DeliveryResponse> findAll(){
        List<Delivery> all = deliveryRepository.findAll();
        return all.stream().map(DeliveryResponse::new).toList();
    }
}
