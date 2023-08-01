package com.example.shopping.order.domain.response;

import com.example.shopping.order.domain.dto.OrderDto;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.product.domain.dto.ProductDto;
import lombok.*;

import java.time.LocalDate;

@Getter
public class OrderResponse extends OrderDto {
    private String orderNum; // 주문번호
    private LocalDate orderDate; //주문일자
    private ProductDto product;

    public OrderResponse(Order order) {
        super(order);
        product = new ProductDto(order.getProduct());
        this.orderNum = order.getOrderNum();
        this.orderDate = order.getOrderDate();
    }
}
