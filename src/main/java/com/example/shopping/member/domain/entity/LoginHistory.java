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
@Builder @Data
public class LoginHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginHistorySeq;
    private LocalDateTime loginTime;
    private boolean isSuccessfulLogin;

    @ManyToOne
    private Member member;
}
