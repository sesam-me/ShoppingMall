package com.example.shopping.review.domain.request;

import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.review.domain.entity.QuestionAndAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAndAnswerRequest {
    private String qTitle;
    private String qContent;
    private String answer;

    public QuestionAndAnswer toEntity(Long productSeq) {
        return QuestionAndAnswer.builder()
                .qTitle(qTitle)
                .qContent(qContent)
                .answer(answer)
                .product(Product.builder().productSeq(productSeq).build())
                .build();
    }
}
