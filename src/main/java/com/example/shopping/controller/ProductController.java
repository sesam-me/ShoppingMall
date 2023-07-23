package com.example.shopping.controller;

import com.example.shopping.domain.entity.Product;
import com.example.shopping.domain.request.ProductRequest;
import com.example.shopping.domain.request.ProductUpdateRequest;
import com.example.shopping.domain.response.ProductResponse;
import com.example.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PutMapping("{product_seq}")
    public ProductResponse update(@PathVariable("product_seq") Long product_seq, @RequestBody ProductUpdateRequest productUpdateRequest) {
        return productService.update(product_seq, productUpdateRequest);
    }
    @PostMapping
    public void save(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
    }

    @DeleteMapping("{product_seq}")
    private void delete(@PathVariable("product_seq") Long product_seq) {
        productService.delete(product_seq);
    }
}
