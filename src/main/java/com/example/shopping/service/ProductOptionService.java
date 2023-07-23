package com.example.shopping.service;

import com.example.shopping.domain.entity.ProductOption;
import com.example.shopping.domain.request.ProductOptionRequest;
import com.example.shopping.repository.ProductOptionRepository;
import com.example.shopping.repository.ProductRepository;
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

    public void saveOption(ProductOptionRequest productOptionRequest) {
        productOptionRepository.save(productOptionRequest.toEntity());
    }

    public void deleteOption(Long option_seq) {
        productOptionRepository.deleteById(option_seq);
    }
}
