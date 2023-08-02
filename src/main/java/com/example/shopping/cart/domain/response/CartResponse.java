package com.example.shopping.cart.domain.response;

import com.example.shopping.cart.domain.dto.CartDto;
import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.product.domain.dto.ProductDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CartResponse extends CartDto {
    private Long cartSeq;
    private Integer cartCount;
    private List<ProductDto> products;
    public CartResponse(Cart cart) {
        super(cart);
        this.cartCount = cart.getCartCount();
        products = cart.getProducts()
                .stream()
                .map(ProductDto::new)
                .toList();
    }
}
