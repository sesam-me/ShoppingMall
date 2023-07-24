package com.example.shopping.product.service;

import com.example.shopping.product.domain.entity.Interest;
import com.example.shopping.product.domain.request.InterestRequest;
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

    public List<Interest> findAllInterest() {
        return interestRepository.findAll();
    }

    public void saveInterest(InterestRequest interestRequest) {
        interestRepository.save(interestRequest.toEntity());
    }

    public void deleteInterest(Long interest_seq) {
        interestRepository.deleteById(interest_seq);
    }
}
