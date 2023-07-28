package com.example.shopping.member.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointInsertDto {
    private Long pointSeq;
    private Long memberSeq;
    private Integer pointBalance;
    private LocalDateTime accumulationDate;
    private LocalDateTime usageDate;
    private LocalDateTime expirationDate;
}

