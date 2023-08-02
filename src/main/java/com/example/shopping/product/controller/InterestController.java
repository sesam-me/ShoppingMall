package com.example.shopping.product.controller;

import com.example.shopping.common.RestResult;
import com.example.shopping.product.domain.entity.Interest;
import com.example.shopping.product.domain.request.InterestRequest;
import com.example.shopping.product.domain.response.InterestResponse;
import com.example.shopping.product.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/interests")
@CrossOrigin("*")
public class InterestController {
    private final InterestService interestService;

    @GetMapping
    public List<InterestResponse> findAllInterest() {
        return interestService.findAllInterest();
    }

    @PostMapping("{productSeq}")
    public ResponseEntity<RestResult<Object>> saveInterest(@RequestBody InterestRequest interestRequest
            , @PathVariable("productSeq") Long productSeq, @RequestParam("memberSeq") Long memberSeq) {
        return interestService.saveInterest(interestRequest,productSeq, memberSeq);
    }

    @DeleteMapping("{memberSeq}/{productSeq}")
    public ResponseEntity<RestResult<Object>> deleteInterest(@PathVariable("memberSeq") Long memberSeq, @PathVariable("productSeq") Long productSeq) {
        return interestService.deleteInterest(memberSeq, productSeq);
    }
}
