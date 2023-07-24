package com.example.shopping.product.controller;

import com.example.shopping.product.domain.entity.ProductOption;
import com.example.shopping.product.domain.request.ProductOptionRequest;
import com.example.shopping.product.service.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/options")
public class ProductOptionController {
    private final ProductOptionService productOptionService;

    @GetMapping
    public List<ProductOption> findAllOption() {
        return productOptionService.findAllOption();
    }

    @PostMapping("{productSeq}")
    public void saveOption(@RequestBody ProductOptionRequest productOptionRequest,
                           @PathVariable("productSeq") Long productSeq) {

        productOptionService.saveOption(productOptionRequest,productSeq);
    }

    @DeleteMapping("{option_seq}")
    public void deleteOption(@PathVariable("option_seq") Long option_seq) {
        productOptionService.deleteOption(option_seq);
    }
}
