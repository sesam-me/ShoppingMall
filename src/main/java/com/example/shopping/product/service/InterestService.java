package com.example.shopping.product.service;

import com.example.shopping.product.domain.entity.Interest;
import com.example.shopping.product.domain.request.InterestRequest;
import com.example.shopping.product.domain.response.InterestResponse;
import com.example.shopping.product.repository.InterestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InterestService {
    private final InterestRepository interestRepository;

    public List<InterestResponse> findAllInterest() {
        List<Interest> all = interestRepository.findAll();
        return all.stream().map(InterestResponse::new).toList();
    }

    public void saveInterest(InterestRequest interestRequest, Long productSeq, Long memberSeq) {
        interestRepository.save(interestRequest.toEntity(productSeq ,memberSeq));
    }

    public void deleteInterest(Long interestSeq) {
        interestRepository.deleteById(interestSeq);
    }
}
