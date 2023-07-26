package com.example.shopping.review.controller;

import com.example.shopping.review.domain.entity.QuestionAndAnswer;
import com.example.shopping.review.domain.request.QuestionAndAnswerRequest;
import com.example.shopping.review.service.QuestionAndAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qas")
public class QuestionAndAnswerController {
    private final QuestionAndAnswerService questionAndAnswerService;

    @GetMapping
    public List<QuestionAndAnswer> findAllQAndA() {
        return questionAndAnswerService.findAllQAndA();
    }

    @PostMapping("{productSeq}")
    public void saveQAndA(@RequestBody QuestionAndAnswerRequest questionAndAnswerRequest,
                          @PathVariable("productSeq")Long productSeq) {
        questionAndAnswerService.saveQAndA(questionAndAnswerRequest, productSeq);
    }

    @DeleteMapping("{qaSeq}")
    public void deleteQAndA(@PathVariable("qaSeq") Long qaSeq) {
        questionAndAnswerService.deleteQAndA(qaSeq);
    }

}
