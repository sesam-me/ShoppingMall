package com.example.shopping.cart.service;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.cart.domain.request.CartRequest;
import com.example.shopping.cart.domain.request.CartUpdateRequest;
import com.example.shopping.cart.domain.response.CartResponse;
import com.example.shopping.cart.repository.CartRepository;
import com.example.shopping.common.RestResult;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

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

    public Cart findById(Long cartSeq) {
        return cartRepository.findById(cartSeq).orElseThrow(() -> new RuntimeException());
    }

    public ResponseEntity<RestResult<Object>> getCarts(Long userSeq) {
        Optional<Member> member = memberRepository.findById(userSeq);

        List<Object[]> cartsWithProductsByMemberSeq = cartRepository.findCartsWithProductsByMemberSeq(userSeq);

        return ResponseEntity.ok(new RestResult<>("success", cartsWithProductsByMemberSeq));
    }

}
