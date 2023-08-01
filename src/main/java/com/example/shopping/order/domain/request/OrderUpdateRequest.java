package com.example.shopping.order.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class OrderUpdateRequest {
    private String orderNum; // 주문번호
    private LocalDate orderDate; //주문일자
}
