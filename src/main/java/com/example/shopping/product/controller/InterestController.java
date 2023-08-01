package com.example.shopping.product.controller;

import com.example.shopping.product.domain.entity.Interest;
import com.example.shopping.product.domain.request.InterestRequest;
import com.example.shopping.product.domain.response.InterestResponse;
import com.example.shopping.product.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/interests")
public class InterestController {
    private final InterestService interestService;

    @GetMapping
    public List<InterestResponse> findAllInterest() {
        return interestService.findAllInterest();
    }

    @PostMapping("{productSeq}")
    public void saveInterest(@RequestBody InterestRequest interestRequest
            ,@PathVariable("productSeq") Long productSeq) {
        interestService.saveInterest(interestRequest,productSeq);
    }

    @DeleteMapping("{interestSeq}")
    public void deleteInterest(@PathVariable("interestSeq") Long interestSeq) {
        interestService.deleteInterest(interestSeq);
    }
}
