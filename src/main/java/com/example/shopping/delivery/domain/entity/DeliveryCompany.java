package com.example.shopping.delivery.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryCompany {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryCompanySeq;
    private String deliveryName;
    private String deliveryContact;
    private String deliveryLocation;
    private String serviceType;
    private String paymentMethod;
    private String paymentStatus;
    private String cardType;
    private LocalDateTime paymentDate;

    @OneToMany(mappedBy = "deliveryCompany")
    private List<Delivery> deliveries;
}
