package com.example.shopping.controller;

import com.example.shopping.domain.entity.Order;
import com.example.shopping.domain.request.OrderRequest;
import com.example.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> findAllOrder() {
        return orderService.findAllOrder();
    }

    @PostMapping
    public void saveOrder(@RequestBody OrderRequest orderRequest) {
        orderService.saveOrder(orderRequest);
    }

    @DeleteMapping("{order_seq}")
    private void deleteOrder(@PathVariable("order_seq") Long order_seq) {
        orderService.deleteOrder(order_seq);
    }
}
