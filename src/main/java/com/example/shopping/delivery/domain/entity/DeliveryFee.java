package com.example.shopping.delivery.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryFee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryFeeSeq;
    private Long deliverySeq;
    private Long deliveryCompanySeq;
    private String redipientAddress;
    private Integer deliveryFee;
    private String deliveryFeeCondition;
}
