package com.example.shopping.cart.service;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.cart.repository.CartRepository;
import com.example.shopping.common.RestError;
import com.example.shopping.common.RestResult;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

//   장바구니 선택? 삭제
    public void deleteCartByCartSeq(Long cartSeq){
        cartRepository.deleteById(cartSeq);
    }

//    장바구니에 상품 추가
    public ResponseEntity<RestResult<Object>> insertCart(Long memberSeq, Long productSeq){
        Cart findByMemberAndProduct = cartRepository.findByMember_MemberSeqAndProduct_ProductSeq(memberSeq, productSeq);
        if (findByMemberAndProduct != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("error", new RestError("duplicate_cart", "이미 장바구니에 추가된 상품입니다.")));
        }
        Cart cart = Cart.builder()
//                .member(findByMemberAndProduct.getMember()) // null이라서 오류.
//               왜 null? 지금 여기서 memberSeq, productSeq를 넣어주는 거임.
//               findByMemberAndProduct 이건 값을 넣어주는게 아니라, 값이 있나없나 확인하는 용
                .member(Member.builder().memberSeq(memberSeq).build())
                .product(Product.builder().productSeq(productSeq).build())
                .build();
        Cart save = cartRepository.save(cart);
        return ResponseEntity.ok(new RestResult<>("success", "장바구니 추가가 완료되었습니다."));
    }
}
