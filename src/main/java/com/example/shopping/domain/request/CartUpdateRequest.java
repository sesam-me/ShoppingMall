package com.example.shopping.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartUpdateRequest {
    private Integer cart_count;
}
