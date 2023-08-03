package com.example.shopping.order.domain.response;

import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.delivery.domain.response.DeliveryResponse;
import com.example.shopping.delivery.domain.response.DeliveryResponse2;
import com.example.shopping.order.domain.dto.OrderDto;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.product.domain.dto.ProductDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
public class OrderResponse extends OrderDto {
    private String orderNum; // 주문번호
    private LocalDate orderDate; //주문일자
    private ProductDto product;
    private List<DeliveryResponse2> delivery;

    public OrderResponse(Order order) {
        super(order);
        product = new ProductDto(order.getProduct());
        delivery = order.getDeliveries().stream().map(DeliveryResponse2::new).toList();
        this.orderNum = order.getOrderNum();
        this.orderDate = order.getOrderDate();
    }
}
