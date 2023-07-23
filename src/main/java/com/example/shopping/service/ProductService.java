package com.example.shopping.service;

import com.example.shopping.domain.entity.Product;
import com.example.shopping.domain.request.ProductRequest;
import com.example.shopping.domain.request.ProductUpdateRequest;
import com.example.shopping.domain.response.ProductResponse;
import com.example.shopping.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(ProductRequest productRequest) {
        productRepository.save(productRequest.ToEntity());
    }

    public ProductResponse update(Long product_seq, ProductUpdateRequest productUpdateRequest) {
        Product product = findById(product_seq);
        product.update(productUpdateRequest.getName(), productUpdateRequest.getImg_url());
        return new ProductResponse(product);
    }
    public void delete(Long product_seq) {
        productRepository.deleteById(product_seq);
    }

    private Product findById(Long productSeq) {
        return productRepository.findById(productSeq).orElseThrow(() -> new RuntimeException());
    }

    //
}
