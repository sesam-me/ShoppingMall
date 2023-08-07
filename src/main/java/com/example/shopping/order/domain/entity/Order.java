package com.example.shopping.order.domain.entity;

import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.product.domain.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime orderDate; //주문일자

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<Delivery> deliveries;

    public void update(String orderNum, LocalDateTime orderDate) {
        this.orderNum = orderNum;
        this.orderDate = orderDate;
    }
}
