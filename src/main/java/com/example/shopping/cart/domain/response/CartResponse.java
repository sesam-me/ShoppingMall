package com.example.shopping.cart.domain.response;

import com.example.shopping.cart.domain.entity.Cart;
import lombok.Getter;

@Getter
public class CartResponse {
    private Long cartSeq;
    private Integer cartCount;

    public CartResponse(Cart cart) {
        this.cartCount = cart.getCartCount();
    }
}
