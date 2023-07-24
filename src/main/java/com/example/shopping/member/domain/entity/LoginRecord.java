package com.example.shopping.member.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginRecordSeq;
    private Long memberSeq;
    private LocalDateTime loginTime;
    private boolean isSuccessfulLogin;

    @ManyToOne
    private Member member;
}
