package com.example.shopping.cart.controller;

import com.example.shopping.cart.service.CartService;
import com.example.shopping.common.RestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;

//       장바구니 선택? 삭제
    @PostMapping({"/{cartSeq}"})
    public void deleteCartByCartSeq(@PathVariable("cartSeq") Long cartSeq){
        cartService.deleteCartByCartSeq(cartSeq);
    }

//        장바구니에 상품 추가
    @PostMapping("/insertCart/{memberSeq}/{productSeq}")
    public ResponseEntity<RestResult<Object>> insetCart(@PathVariable("memberSeq")Long memberSeq, @PathVariable("productSeq")Long productSeq){
        return cartService.insertCart(memberSeq, productSeq);
    }

}
