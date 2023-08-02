package com.example.shopping.cart.domain.dto;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.product.domain.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartDto {
    private Long cartSeq;
    private Integer cartCount;

    public CartDto(Cart cart) {
        this.cartSeq = cart.getCartSeq();
        this.cartCount = cart.getCartCount();
    }
}
