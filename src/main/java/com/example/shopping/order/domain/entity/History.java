package com.example.shopping.order.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="histories")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long history_seq;
    @Column(columnDefinition = "TEXT")
    private String cancel_content; //주문취소사유
    @Column(columnDefinition = "TEXT")
    private String refund_content; //주문환불사유
    private LocalDate history_date;//취소환불일자
}
