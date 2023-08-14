package com.example.shopping.product.service;

import com.example.shopping.common.RestResult;
import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.request.ProductRequest;
import com.example.shopping.product.domain.request.ProductUpdateRequest;
import com.example.shopping.product.domain.response.ProductResponse;
import com.example.shopping.product.domain.response.ProductResponse2;
import com.example.shopping.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductResponse> findAll(PageRequest request) {
        Page<Product> productPage = productRepository.findAll(request);
        List<ProductResponse> productResponses = productPage
                .map(product -> new ProductResponse(product))
                .toList();
        // PageImpl클래스를 사용하여 productResponses리스트와 페이지 정보 총 엔티티 수를 이용하여 새로운 Page객체를 생성하고 반환
        // new PageImpl<>(페이지 내용, 페이지정보, 총 아이템 수)
        return new PageImpl<>(productResponses, request, productPage.getTotalElements());
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


    public ProductResponse getProductResponse(Long productSeq) {
        Optional<Product> productResponse = productRepository.findById(productSeq);
        return productResponse.map(ProductResponse::new).orElse(null);
    }


//  # 방법 1) 상품명으로 상품 검색
    public List<ProductResponse> searchProductByKeyword(String productName) {
        Specification<Product> spec = ProductSpecifications.byKeyword(productName);
        List<Product> all = productRepository.findAll(spec);
        return all.stream().map(ProductResponse::new).toList();
    }


//    # 방법 2)
    public List<ProductResponse> searchProductByHnameAndEname(String productName){
        List<Product> products = productRepository.searchProductByHnameAndEname(productName);
        return products.stream().map(ProductResponse::new).toList();
    }


//    사이즈, 컬러 검색
    public ResponseEntity<RestResult<Object>> searchBySizeAndColor(List<Integer> size, List<String> color){
        List<Object[]> products = productRepository.searchSizeAndColor(size, color);

        List list = new ArrayList();

        for (Object[] row : products) {
                ProductResponse2 productResponse2 = ProductResponse2.builder()
                    .hName((String) row[0])
                    .eName((String) row[1])
                    .imgUrl((String) row[2])
                    .detailImgUrl((String) row[3])
                    .createAt((LocalDate) row[4])
                    .brand((String) row[5])
                    .price((Integer) row[6])
                    .size((Integer) row[7])
                    .color((String) row[8])
                    .build();
            list.add(productResponse2);
        }

        return ResponseEntity.ok(new RestResult<>("success", list));
//        return products.stream().map(ProductResponse2::new).toList();
    }


}

