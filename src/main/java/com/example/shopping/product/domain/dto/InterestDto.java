package com.example.shopping.product.domain.dto;

import com.example.shopping.product.domain.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InterestDto {
    private Long interestSeq;
    private LocalDate interestDate;
    private Integer interestLike;


    public InterestDto(Interest interest) {
        this.interestSeq = interest.getInterestSeq();
        this.interestDate = interest.getInterestDate();
        this.interestLike = interest.getInterestLike();

    }
}
