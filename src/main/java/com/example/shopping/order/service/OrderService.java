package com.example.shopping.order.service;

import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.order.domain.request.OrderRequest;
import com.example.shopping.order.domain.response.OrderResponse;
import com.example.shopping.order.repository.OrderRepository;
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
}
