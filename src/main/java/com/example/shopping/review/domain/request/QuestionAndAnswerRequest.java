package com.example.shopping.review.domain.request;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.review.domain.entity.QuestionAndAnswer;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAndAnswerRequest {
    private String title;
    private String content;
    private String answer;

    public QuestionAndAnswer toEntity(Long productSeq, Long memberSeq) {

        return QuestionAndAnswer.builder()
                .title(title)
                .content(content)
                .answer(answer)
                .product(Product.builder().productSeq(productSeq).build())
                .member(Member.builder().memberSeq(memberSeq).build())
                .build();
    }
}
