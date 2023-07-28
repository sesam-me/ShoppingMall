package com.example.shopping.order.domain.dto;

import com.example.shopping.order.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long orderSeq;
    private String orderNum; // 주문번호
    private LocalDate orderDate; //주문일자
//    private Long productSeq;

    public OrderDto(Order order) {
        this.orderSeq = order.getOrderSeq();
        this.orderNum = order.getOrderNum();
        this.orderDate = order.getOrderDate();
//        this.productSeq = order.getProduct().getProductSeq();
    }
}
