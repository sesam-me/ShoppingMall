package com.example.shopping.domain.request;

import com.example.shopping.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String order_num; // 주문번호
    private LocalDate order_date; //주문일자

    public Order toEntity() {
        return Order.builder()
                .order_num(order_num)
                .order_date(order_date)
                .build();
    }
}
