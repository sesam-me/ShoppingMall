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

    public List<Cart> findAllCart() {
        return cartRepository.findAll();
    }

    public void saveCart(CartRequest cartRequest) {
        cartRepository.save(cartRequest.toEntity());
    }

    public void deleteCart(Long cart_seq) {
        cartRepository.deleteById(cart_seq);
    }

    public CartResponse updateCart(Long cart_seq, CartUpdateRequest cartUpdateRequest) {
        Cart cart = findById(cart_seq);
        cart.update(cartUpdateRequest.getCart_count());
        return new CartResponse(cart);
    }

    private Cart findById(Long cartSeq) {
        return cartRepository.findById(cartSeq).orElseThrow(() -> new RuntimeException());
    }
}
