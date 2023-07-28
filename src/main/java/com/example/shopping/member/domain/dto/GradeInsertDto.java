package com.example.shopping.member.domain.dto;

import com.example.shopping.member.domain.entity.Member;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeInsertDto {
    private Long gradeSeq;
    private Long memberSeq;
    private String gradeName;

    @OneToOne
    private Member member;
}
