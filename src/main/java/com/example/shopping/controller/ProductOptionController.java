package com.example.shopping.controller;

import com.example.shopping.domain.entity.ProductOption;
import com.example.shopping.domain.request.ProductOptionRequest;
import com.example.shopping.service.ProductOptionService;
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

    @PostMapping
    public void saveOption(@RequestBody ProductOptionRequest productOptionRequest) {
        productOptionService.saveOption(productOptionRequest);
    }

    @DeleteMapping("{option_seq}")
    public void deleteOption(@PathVariable("option_seq") Long option_seq) {
        productOptionService.deleteOption(option_seq);
    }
}
