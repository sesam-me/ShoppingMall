package com.example.shopping.order.domain.response;

import com.example.shopping.order.domain.dto.OrderDto;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.product.domain.dto.ProductDto;
import lombok.*;

import java.time.LocalDate;

@Getter
public class OrderResponse extends OrderDto {
    private ProductDto product;

    public OrderResponse(Order order) {
        super(order);
        product = new ProductDto(order.getProduct());
    }
}
