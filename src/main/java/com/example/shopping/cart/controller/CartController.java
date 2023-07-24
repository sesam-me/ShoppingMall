package com.example.shopping.cart.controller;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.cart.domain.request.CartRequest;
import com.example.shopping.cart.domain.request.CartUpdateRequest;
import com.example.shopping.cart.domain.response.CartResponse;
import com.example.shopping.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<Cart> findAllCard() {
        return cartService.findAllCart();
    }

    @PostMapping
    public void saveCart(@RequestBody CartRequest cartRequest) {
        cartService.saveCart(cartRequest);
    }

    @DeleteMapping("{cart_seq}")
    public void deleteCart(@PathVariable("cart_seq") Long cart_seq) {
        cartService.deleteCart(cart_seq);
    }

    @PutMapping("{cart_seq}")
    public CartResponse updateCart(@PathVariable("cart_seq") Long cart_seq, @RequestBody CartUpdateRequest cartUpdateRequest) {
        return cartService.updateCart(cart_seq, cartUpdateRequest);
    }
}
