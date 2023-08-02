package com.example.shopping.cart.service;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.cart.domain.request.CartRequest;
import com.example.shopping.cart.domain.request.CartUpdateRequest;
import com.example.shopping.cart.domain.response.CartResponse;
import com.example.shopping.cart.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartService {
    private final CartRepository cartRepository;

    public List<CartResponse> findAllCart() {
        List<Cart> all = cartRepository.findAll();
        return all.stream().map(CartResponse::new).toList();
    }

    public void saveCart(CartRequest cartRequest) {
        cartRepository.save(cartRequest.toEntity());
    }

    public void deleteCart(Long cartSeq) {
        cartRepository.deleteById(cartSeq);
    }

    public CartResponse updateCart(Long cartSeq, CartUpdateRequest cartUpdateRequest) {
        Cart cart = findById(cartSeq);
        cart.update(cartUpdateRequest.getCartCount());
        return new CartResponse(cart);
    }

    private Cart findById(Long cartSeq) {
        return cartRepository.findById(cartSeq).orElseThrow(() -> new RuntimeException());
    }
}
