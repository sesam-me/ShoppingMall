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
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderResponse> findAllOrder() {
        return orderService.findAllOrder();
    }

    @GetMapping("{memberSeq}")
    public List<OrderResponse> findByMemberSeq(@PathVariable Long memberSeq) {
        return orderService.findByMemberSeq(memberSeq);
    }

    @PostMapping("{productSeq}/{memberSeq}")
    public void saveOrder(@RequestBody OrderRequest orderRequest, @PathVariable("productSeq") Long productSeq, @PathVariable("memberSeq") Long memberSeq) {
        orderService.saveOrder(orderRequest, productSeq, memberSeq);
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
