package com.example.shopping.product.service;

import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.request.ProductRequest;
import com.example.shopping.product.domain.request.ProductUpdateRequest;
import com.example.shopping.product.domain.response.ProductResponse;
import com.example.shopping.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse> findAll() {
        List<Product> all = productRepository.findAll();
        return all.stream().map(ProductResponse::new).toList();
    }

    public void save(ProductRequest productRequest) {
        productRepository.save(productRequest.ToEntity());
    }

    public ProductResponse update(Long productSeq, ProductUpdateRequest productUpdateRequest) {
        Product product = findById(productSeq);
        product.update(productUpdateRequest.getHName(), productUpdateRequest.getEName(), productUpdateRequest.getImgUrl(),productUpdateRequest.getBrand(), productUpdateRequest.getDetailImgUrl(), productUpdateRequest.getPrice());
        return new ProductResponse(product);
    }
    public void delete(Long productSeq) {
        productRepository.deleteById(productSeq);
    }

    private Product findById(Long productSeq) {
        return productRepository.findById(productSeq).orElseThrow(() -> new RuntimeException());
    }

//  # 방법 1) 상품명으로 상품 검색
    public List<ProductResponse> searchProductByKeyword(String productName) {
        Specification<Product> spec = ProductSpecifications.byKeyword(productName);
        List<Product> all = productRepository.findAll(spec);
        return all.stream().map(ProductResponse::new).toList();
    }


//    # 방법 2)
    public List<ProductResponse> searchProductByHnameAndEname(String productName){
        List<Product> product = productRepository.searchProductByHnameAndEname(productName);
        return product.stream().map(ProductResponse::new).toList();
    }
}

