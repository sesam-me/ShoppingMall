package com.example.shopping.product.service;

import com.example.shopping.common.RestError;
import com.example.shopping.common.RestResult;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.repository.MemberRepository;
import com.example.shopping.product.domain.entity.Interest;
import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.request.InterestRequest;
import com.example.shopping.product.domain.response.InterestResponse;
import com.example.shopping.product.repository.InterestRepository;
import com.example.shopping.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InterestService {
    private final InterestRepository interestRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public List<InterestResponse> findAllInterest() {
        List<Interest> all = interestRepository.findAll();
        return all.stream().map(InterestResponse::new).toList();
    }

    public ResponseEntity<RestResult<Object>> saveInterest(InterestRequest interestRequest, Long productSeq, Long memberSeq) {
        Interest interest = interestRepository.findByProductProductSeqAndMemberMemberSeq(productSeq,memberSeq);

        if (interest != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResult<>("error",new RestError("BAD_REQUEST","이미 관심상품에 추가 하였습니다.")));
        }

        interestRepository.save(interestRequest.toEntity(productSeq ,memberSeq));

        return ResponseEntity.ok(new RestResult<>("success", "관심상품이 등록되었습니다."));

    }

    public ResponseEntity<RestResult<Object>> deleteInterest(Long memberSeq, Long productSeq) {
        Optional<Member> member = memberRepository.findById(memberSeq);
        Optional<Product> product = productRepository.findById(productSeq);

        Interest interest = interestRepository.findByProductAndMember(product.get(),member.get());

        interestRepository.delete(interest);
        return ResponseEntity.ok(new RestResult<>("success", "관심상품이 삭제되었습니다."));
    }
}
