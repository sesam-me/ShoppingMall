package com.example.shopping.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "options")
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionSeq;
    private Integer size;

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private Product product;

}
