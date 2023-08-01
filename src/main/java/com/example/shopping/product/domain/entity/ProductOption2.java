package com.example.shopping.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="options2")
public class ProductOption2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionSeq;
    private String color;

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private Product product;
}
