package com.example.shopping.member.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Grade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeSeq;
    private String gradeName;
    private String userId;
}
