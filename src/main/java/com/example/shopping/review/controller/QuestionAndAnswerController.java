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
@CrossOrigin("*")
public class QuestionAndAnswerController {
    private final QuestionAndAnswerService questionAndAnswerService;

    @GetMapping
    public List<QuestionAndAnswer> findAllQAndA() {
        return questionAndAnswerService.findAllQAndA();
    }

    @PostMapping("{productSeq}/{memberSeq}")
    public void saveQAndA(@RequestBody QuestionAndAnswerRequest questionAndAnswerRequest,
                          @PathVariable("productSeq")Long productSeq, @PathVariable("memberSeq")Long memberSeq) {
        questionAndAnswerService.saveQAndA(questionAndAnswerRequest, productSeq, memberSeq);
    }

    @DeleteMapping("{qaSeq}")
    public void deleteQAndA(@PathVariable("qaSeq") Long qaSeq) {
        questionAndAnswerService.deleteQAndA(qaSeq);
    }

}
