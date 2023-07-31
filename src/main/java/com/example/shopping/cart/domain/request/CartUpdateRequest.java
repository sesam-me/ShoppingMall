package com.example.shopping.cart.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartUpdateRequest {
    private Integer cartCount;
}
