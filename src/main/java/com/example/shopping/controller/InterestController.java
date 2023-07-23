package com.example.shopping.controller;

import com.example.shopping.domain.entity.Interest;
import com.example.shopping.domain.request.InterestRequest;
import com.example.shopping.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/interests")
public class InterestController {
    private final InterestService interestService;

    @GetMapping
    public List<Interest> findAllInterest() {
        return interestService.findAllInterest();
    }

    @PostMapping
    public void saveInterest(@RequestBody InterestRequest interestRequest) {
        interestService.saveInterest(interestRequest);
    }

    @DeleteMapping("{interest_seq}")
    public void deleteInterest(@PathVariable("interest_seq") Long interest_seq) {
        interestService.deleteInterest(interest_seq);
    }
}
