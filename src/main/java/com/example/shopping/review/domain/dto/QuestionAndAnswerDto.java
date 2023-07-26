package com.example.shopping.review.domain.dto;

import com.example.shopping.review.domain.entity.QuestionAndAnswer;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionAndAnswerDto {
    private Long qaSeq;
    private String qTitle; //질문 제목
    private String qContent; //질문 내용
    private String answer; //답변
    private Long productSeq;

    public QuestionAndAnswerDto(QuestionAndAnswer questionAndAnswer) {
        this.qaSeq = questionAndAnswer.getQaSeq();
        this.qTitle = questionAndAnswer.getQTitle();
        this.qContent = questionAndAnswer.getQContent();
        this.answer = questionAndAnswer.getAnswer();
        this.productSeq = questionAndAnswer.getProduct().getProductSeq();
    }
}
