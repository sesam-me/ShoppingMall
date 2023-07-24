package com.example.shopping.member.domain.entity;

import com.example.shopping.delivery.domain.entity.Delivery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "members")
@AllArgsConstructor @NoArgsConstructor
@Builder
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;
    private String id;
    private String password;
    private String username;
    private boolean gender;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    private String address;

    @OneToMany(mappedBy = "member")
    private List<Delivery> deliveries;
}
