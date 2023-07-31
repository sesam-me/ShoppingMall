package com.example.shopping.order.controller;

import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.order.domain.request.HistoryRequest;
import com.example.shopping.order.domain.request.OrderRequest;
import com.example.shopping.order.domain.request.OrderUpdateRequest;
import com.example.shopping.order.domain.response.OrderResponse;
import com.example.shopping.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderResponse> findAllOrder() {
        return orderService.findAllOrder();
    }

    @PostMapping("{productSeq}")
    public void saveOrder(@RequestBody OrderRequest orderRequest, @RequestBody HistoryRequest historyRequest, @PathVariable("productSeq") Long productSeq) {
        orderService.saveOrder(orderRequest,historyRequest ,productSeq);
    }

    @DeleteMapping("{orderSeq}")
    private void deleteOrder(@PathVariable("orderSeq") Long orderSeq) {
        orderService.deleteOrder(orderSeq);
    }

    @PutMapping("{orderSeq}")
    public OrderResponse updateOrder(@PathVariable("orderSeq") Long orderSeq, @RequestBody OrderUpdateRequest orderUpdateRequest) {
        return orderService.updateOrder(orderSeq, orderUpdateRequest);
    }
}
