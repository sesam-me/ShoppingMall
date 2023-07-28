package com.example.shopping.order.domain.entity;

import com.example.shopping.product.domain.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSeq;
    private String orderNum; // 주문번호
    private LocalDate orderDate; //주문일자

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private Product product;
}
