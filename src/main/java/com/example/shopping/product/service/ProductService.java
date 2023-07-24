package com.example.shopping.product.service;

import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.request.ProductRequest;
import com.example.shopping.product.domain.request.ProductUpdateRequest;
import com.example.shopping.product.domain.response.ProductResponse;
import com.example.shopping.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
