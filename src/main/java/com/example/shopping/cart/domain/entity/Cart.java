package com.example.shopping.cart.domain.entity;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.product.domain.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartSeq;

    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;


}
