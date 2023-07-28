package com.example.shopping.member.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Point {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointSeq;
    private Integer pointBalance;
    private LocalDateTime accumulationDate;
    private LocalDateTime usageDate;
    private LocalDateTime expirationDate;
    private String userId;

}
