package com.example.shopping.payment.controller;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.payment.domain.dto.PaymentInsertDto;
import com.example.shopping.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("insert")
    public void paymentInsert(@RequestBody PaymentInsertDto paymentInsertDto, Member member){
//        paymentService.paymentInsert(paymentInsertDto, member);
    }
}
