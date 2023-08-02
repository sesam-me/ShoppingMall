package com.example.shopping.cart.domain.request;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private Integer cartCount;


    public Cart toEntity() {
        return Cart.builder()
                .cartCount(cartCount)
                .build();
    }
}
