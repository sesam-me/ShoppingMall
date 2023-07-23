package com.example.shopping.domain.request;

import com.example.shopping.domain.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterestRequest {
    private LocalDate interest_date;
    private Integer interest_like;

    public Interest toEntity() {
        return Interest.builder()
                .interest_date(interest_date)
                .interest_like(interest_like)
                .build();
    }

}
