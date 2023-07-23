package com.example.shopping.domain.request;

import com.example.shopping.domain.entity.QuestionAndAnswer;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAndAnswerRequest {
    private String q_title;
    private String q_content;
    private String answer;

    public QuestionAndAnswer toEntity() {
        return QuestionAndAnswer.builder()
                .q_title(q_title)
                .q_content(q_content)
                .answer(answer)
                .build();
    }
}
