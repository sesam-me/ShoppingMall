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

    @PostMapping
    public void saveQAndA(@RequestBody QuestionAndAnswerRequest questionAndAnswerRequest) {
        questionAndAnswerService.saveQAndA(questionAndAnswerRequest);
    }

    @DeleteMapping("{qa_seq}")
    public void deleteQAndA(@PathVariable("qa_seq") Long qa_seq) {
        questionAndAnswerService.deleteQAndA(qa_seq);
    }

}
