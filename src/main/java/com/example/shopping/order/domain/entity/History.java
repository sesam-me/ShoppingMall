package com.example.shopping.order.domain.entity;

import com.example.shopping.product.domain.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="orderHistories")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historySeq;
    @Column(columnDefinition = "TEXT")
    private String cancelContent; //주문취소사유
    @Column(columnDefinition = "TEXT")
    private String refundContent; //주문환불사유
    private LocalDate historyDate;//취소환불일자

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private Product product;
}
