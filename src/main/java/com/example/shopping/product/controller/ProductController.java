package com.example.shopping.product.controller;

import com.example.shopping.common.RestResult;
import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.request.ProductRequest;
import com.example.shopping.product.domain.request.ProductUpdateRequest;
import com.example.shopping.product.domain.response.ProductResponse;
import com.example.shopping.product.domain.response.ProductResponse2;
import com.example.shopping.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @PutMapping("{productSeq}")
    public ProductResponse update(@PathVariable("productSeq") Long productSeq, @RequestBody ProductUpdateRequest productUpdateRequest) {
        return productService.update(productSeq, productUpdateRequest);
    }
    @PostMapping
    public void save(@RequestBody ProductRequest productRequest) {
        System.out.println(productRequest.toString());

        productService.save(productRequest);
    }

    @DeleteMapping("{productSeq}")
    private void delete(@PathVariable("productSeq") Long productSeq) {
        productService.delete(productSeq);
    }

// 방법1) 상품검색 : jpa, 따로 기억을 하고 있기 때문에 계속 DB를 가지 않아도 됨.
    @GetMapping("/searchProduct")
    public List<ProductResponse> searchProductByKeyword(@RequestParam("productName")String productName){
        return productService.searchProductByKeyword(productName);
    }
// 방법2) 상품검색 : query, 쿼리를 따로 만들었기 때문에 jpa에서 저장하고 있는 것이 아니라서 검색할 때마다 DB에 검색함.
    @GetMapping("/searchProduct2")
    public List<ProductResponse> searchProductByHnameAndEname(@RequestParam("productName")String productName){
        return productService.searchProductByHnameAndEname(productName);
    }

//    사이즈, 컬러 검색
    @GetMapping("/searchBySizeColor")
    public ResponseEntity<RestResult<Object>> searchBySizeAndColor(@RequestParam("size")List<Integer> size, @RequestParam("color")List<String> color){
        return productService.searchBySizeAndColor(size, color);
    }


}
