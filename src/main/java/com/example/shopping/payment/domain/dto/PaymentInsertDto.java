package com.example.shopping.payment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInsertDto {
    private Long memberSeq;
    private int paymentAmount;
    private String paymentMethod;
    private String paymentStatus;
    private String cardType;
    private LocalDateTime paymentDate;
}
