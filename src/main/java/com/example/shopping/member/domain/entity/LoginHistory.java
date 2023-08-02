package com.example.shopping.member.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "loginHistories")
@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
public class LoginHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginHistorySeq;
    private LocalDateTime loginTime;
    private boolean isSuccessfulLogin;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    @JsonIgnore
    private Member member;
}
