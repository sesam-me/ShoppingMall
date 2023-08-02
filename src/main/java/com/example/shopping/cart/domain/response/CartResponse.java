package com.example.shopping.cart.domain.response;

import com.example.shopping.cart.domain.dto.CartDto;
import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.product.domain.dto.ProductDto;
import com.example.shopping.product.domain.entity.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class CartResponse extends CartDto {
    private Long cartSeq;
    private Integer cartCount;
    private Product products;
    public CartResponse(Cart cart) {
        super(cart);
        this.cartCount = cart.getCartCount();
        this.products = cart.getProducts();
    }
}
