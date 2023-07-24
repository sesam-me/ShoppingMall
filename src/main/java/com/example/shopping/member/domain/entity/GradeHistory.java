package com.example.shopping.member.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Builder
public class GradeHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeHistorySeq;
    private Long memberSeq;
    private Long greadSeq;
    private Integer previousGrade;
    private Integer currentGrade;
    private LocalDateTime gradeChangeDate;
    private String reasonForChange;
}
