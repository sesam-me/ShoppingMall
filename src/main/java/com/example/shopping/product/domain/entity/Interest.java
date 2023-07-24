package com.example.shopping.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="interests")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interest_seq;
    private LocalDate interest_date; //관심 표현한 날짜
    private Integer interest_like; // 관심 상태 좋아요인지 아닌지
}
