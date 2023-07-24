package com.example.shopping.cart.domain.response;

import com.example.shopping.cart.domain.entity.Cart;

public class CartResponse {
    private Long cart_seq;
    private Integer cart_count;

    public CartResponse(Cart cart) {
        this.cart_count = cart.getCart_count();
    }
}
