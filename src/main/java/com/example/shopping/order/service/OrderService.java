package com.example.shopping.order.service;

import com.example.shopping.common.RestError;
import com.example.shopping.common.RestResult;
import com.example.shopping.inventory.domain.entity.Inventory;
import com.example.shopping.inventory.repository.InventoryRepository;
import com.example.shopping.order.domain.entity.History;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.order.domain.request.HistoryRequest;
import com.example.shopping.order.domain.request.HistoryUpdateRequest;
import com.example.shopping.order.domain.request.OrderRequest;
import com.example.shopping.order.domain.request.OrderUpdateRequest;
import com.example.shopping.order.domain.response.HistoryResponse;
import com.example.shopping.order.domain.response.OrderResponse;
import com.example.shopping.order.repository.HistoryRepository;
import com.example.shopping.order.repository.OrderRepository;
import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.response.ProductResponse;
import com.example.shopping.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final HistoryRepository historyRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public List<OrderResponse> findAllOrder() {
        List<Order> all = orderRepository.findAll();
        return all.stream().map(OrderResponse::new).toList();
    }

    public ResponseEntity<RestResult<Object>> saveOrder(OrderRequest orderRequest, Long productSeq) {
        // 유저의 잔액을 조회 해야힘..
        // 잔액이 상품의 가격보다 낮다 ? return
        try {
            orderRepository.save(orderRequest.toEntity(productSeq));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }
        //
        try {
            History history = History.builder()
                    .historyDate(null)
                    .cancelContent(null)
                    .refundContent(null)
                    .build();
            historyRepository.save(history);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }

        // 재고를 체크..
//        재고테이블에서 빠지는 로직;
        try {
            Product product = productRepository.findById(productSeq)
                    .orElseThrow(()->new RuntimeException("그런 상품 없다"));

            Optional<Inventory> inventory = inventoryRepository.findByProduct(product);

            // 예외처리..
            if (inventory.get().getCount() < 1) {
                throw new RuntimeException("재고가 없어요");
            }

            Inventory inventory1 = Inventory.builder()
                    .count(inventory.get().getCount() -1)
                    .sales(inventory.get().getSales() + 1)
                    .waste(inventory.get().getWaste())
                    .build();

            inventoryRepository.save(inventory1);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }

        // 전부 다 정상적이라면 ..?
        return ResponseEntity.ok(new RestResult<>("success", "주문성공"));

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

