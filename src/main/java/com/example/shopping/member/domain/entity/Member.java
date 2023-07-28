package com.example.shopping.member.domain.entity;

import com.example.shopping.delivery.domain.entity.Delivery;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "members")
@AllArgsConstructor @NoArgsConstructor
@Builder
@Data
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;

    @Column(unique = true)
    private String id;
    private String password;
    private String username;
    private boolean gender;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    private String address;

    @OneToMany(mappedBy = "member")
    private List<Delivery> deliveries;

    @OneToOne
    @JoinColumn(name = "point_seq")
    private Point point;

    @OneToOne
    @JoinColumn(name = "grade_seq")
    private Grade grade;

    @Override
    public String toString() {
        return "Member{" +
                "memberSeq=" + memberSeq +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", registrationDate=" + registrationDate +
                ", address='" + address + '\'' +
                // Skip 'deliveries', 'point', and 'grade' to avoid recursion
                '}';
    }

}
