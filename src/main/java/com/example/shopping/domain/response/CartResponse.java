package com.example.shopping.domain.response;

import com.example.shopping.domain.entity.Cart;

public class CartResponse {
    private Long cart_seq;
    private Integer cart_count;

    public CartResponse(Cart cart) {
        this.cart_count = cart.getCart_count();
    }
}
