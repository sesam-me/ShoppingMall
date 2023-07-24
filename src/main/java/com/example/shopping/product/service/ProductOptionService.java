package com.example.shopping.product.service;

import com.example.shopping.product.domain.entity.ProductOption;
import com.example.shopping.product.domain.request.ProductOptionRequest;
import com.example.shopping.product.repository.ProductOptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductOptionService {
    private final ProductOptionRepository productOptionRepository;

    public List<ProductOption> findAllOption() {
        return productOptionRepository.findAll();
    }

    public void saveOption(ProductOptionRequest productOptionRequest, Long productSeq) {
        productOptionRepository.save(productOptionRequest.toEntity(productSeq));
    }

    public void deleteOption(Long optionSeq) {
        productOptionRepository.deleteById(optionSeq);
    }
}
