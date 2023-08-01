package com.example.shopping.order.domain.request;

import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.product.domain.entity.Product;
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
    private String orderNum; // 주문번호
    private LocalDate orderDate; //주문일자

    public Order toEntity(Long productSeq) {
        return Order.builder()
                .orderNum(orderNum)
                .orderDate(orderDate)
                .product(Product.builder().productSeq(productSeq).build())
                .build();
    }
}
