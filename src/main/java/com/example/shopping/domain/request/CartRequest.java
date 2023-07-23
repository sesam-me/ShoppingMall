package com.example.shopping.domain.request;

import com.example.shopping.domain.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private Integer cart_count;

    public Cart toEntity() {
        return Cart.builder()
                .cart_count(cart_count)
                .build();
    }
}
