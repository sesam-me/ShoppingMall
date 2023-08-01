package com.example.shopping.payment.service;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.repository.MemberRepository;
import com.example.shopping.payment.domain.dto.PaymentInsertDto;
import com.example.shopping.payment.domain.entity.Payment;
import com.example.shopping.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    public void paymentInsert(PaymentInsertDto paymentInsertDto, Member member){
        Optional<Member> findByMemberSeq = memberRepository.findById(member.getMemberSeq());

        Payment payment = Payment.builder()
                .member(findByMemberSeq.get())
                .paymentAmount(paymentInsertDto.getPaymentAmount())
                .paymentMethod(paymentInsertDto.getPaymentMethod())
                .paymentStatus(paymentInsertDto.getPaymentStatus())
                .cardType(paymentInsertDto.getCardType())
                .paymentDate(paymentInsertDto.getPaymentDate())
                .build();
        paymentRepository.save(payment);
    }


}
