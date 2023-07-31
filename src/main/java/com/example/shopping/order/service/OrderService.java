package com.example.shopping.order.service;

import com.example.shopping.inventory.repository.InventoryRepository;
import com.example.shopping.order.domain.entity.History;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.order.domain.request.HistoryUpdateRequest;
import com.example.shopping.order.domain.request.OrderRequest;
import com.example.shopping.order.domain.request.OrderUpdateRequest;
import com.example.shopping.order.domain.response.HistoryResponse;
import com.example.shopping.order.domain.response.OrderResponse;
import com.example.shopping.order.repository.HistoryRepository;
import com.example.shopping.order.repository.OrderRepository;
import com.example.shopping.product.domain.response.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final HistoryRepository historyRepository;
    private final InventoryRepository inventoryRepository;

    public List<OrderResponse> findAllOrder() {
        List<Order> all = orderRepository.findAll();
        return all.stream().map(OrderResponse::new).toList();
    }

    public void saveOrder(OrderRequest orderRequest, Long productSeq) {
        orderRepository.save(orderRequest.toEntity(productSeq));
    }

    public void deleteOrder(Long orderSeq) {
        orderRepository.deleteById(orderSeq);
    }

    public OrderResponse updateOrder(Long orderSeq, OrderUpdateRequest orderUpdateRequest) {
        Order order = findById(orderSeq);
        order.update(orderUpdateRequest.getOrderNum(), orderUpdateRequest.getOrderDate());
        return new OrderResponse(order);
    }

    private Order findById(Long orderSeq) {
        return orderRepository.findById(orderSeq).orElseThrow(() -> new RuntimeException());
    }
}

