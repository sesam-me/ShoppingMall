package com.example.shopping.order.service;

import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.order.domain.request.OrderRequest;
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

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    public void saveOrder(OrderRequest orderRequest) {
        orderRepository.save(orderRequest.toEntity());
    }

    public void deleteOrder(Long order_seq) {
        orderRepository.deleteById(order_seq);
    }
}
