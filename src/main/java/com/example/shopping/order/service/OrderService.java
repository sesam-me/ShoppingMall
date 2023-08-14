package com.example.shopping.order.service;

import com.example.shopping.common.RestError;
import com.example.shopping.common.RestResult;
import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.delivery.repository.DeliveryRepository;
import com.example.shopping.inventory.domain.entity.Inventory;
import com.example.shopping.inventory.repository.InventoryRepository;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.repository.MemberRepository;
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
import com.example.shopping.payment.domain.entity.Payment;
import com.example.shopping.payment.domain.entity.PaymentHistory;
import com.example.shopping.payment.repository.PaymentHistoryRepository;
import com.example.shopping.payment.repository.PaymentRepository;
import com.example.shopping.product.domain.dto.ProductDto;
import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.response.ProductResponse;
import com.example.shopping.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final HistoryRepository historyRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final DeliveryRepository deliveryRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final MemberRepository memberRepository;
    private final TaskScheduler taskScheduler;

    private ScheduledFuture<?> scheduledDeliveryTask; // ScheduledFuture 객체


    public List<OrderResponse> findByMemberSeq(Long memberSeq) {
        Optional<Member> byId = memberRepository.findById(memberSeq);
        List<Order> byMember = orderRepository.findAllByMember(byId.get());
        return byMember.stream().map(OrderResponse::new).toList();
    }

    public List<OrderResponse> findAllOrder() {
        List<Order> all = orderRepository.findAll();
        return all.stream().map(OrderResponse::new).toList();
    }

    public ResponseEntity<RestResult<Object>> saveOrder(OrderRequest orderRequest, Long productSeq, Long memberSeq) {

        Payment paymentSave;
        Order orderSave;
        Product productSave;

        // 주문 테이블 적재..
        try {
            orderSave = orderRepository.save(orderRequest.toEntity(productSeq, memberSeq));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }


        // 주문 이력 테이블에 적재..
        try {
            History history = History.builder()
                    .historyDate(null)
                    .cancelContent(null)
                    .refundContent(null)
                    .product(Product.builder().productSeq(productSeq).build())
                    .build();

            historyRepository.save(history);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }

        // 재고를 테이블 재고 판매수량 update..
        try {
            productSave = productRepository.findById(productSeq)
                    .orElseThrow(()->new RuntimeException("We_don't_have_product"));

            Optional<Inventory> inventory = inventoryRepository.findByProduct(productSave);
            // 예외처리..
            if (inventory.get().getCount() < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new RestResult<>("bed_request", new RestError("bed_request", "no_more_inventory")));
            }
            Inventory inventory1 = Inventory.builder()
                    .inventorySeq(inventory.get().getInventorySeq())
                    .product(Product.builder().productSeq(productSeq).build())
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

        // 결제 테이블 적재..
        try {
            Payment payment = Payment.builder()
                    .paymentAmount(orderRequest.getPaymentAmount())
                    .paymentMethod(orderRequest.getPaymentMethod())
                    .paymentStatus(orderRequest.getPaymentStatus())
                    .cardType(orderRequest.getCardType())
                    .member(Member.builder().memberSeq(orderRequest.getMemberSeq()).build())
                    .paymentDate(orderRequest.getPaymentDate())
                    .build();

            paymentSave = paymentRepository.saveAndFlush(payment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }

        // 결제 이력 테이블 적재..
        try {
            PaymentHistory paymentHistory = PaymentHistory.builder()
                    .paymentSeq(productSeq)
                    .build();
            paymentHistoryRepository.save(paymentHistory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }

        // 배송 테이블 적재.. (member_seq, order_seq, product_seq, payment_seq 필요..)
        try {

            Delivery delivery = Delivery.builder()
                    .deliveryStatus(1)
                    .deliveryDate(null)
                    .recipientInformation(null)
                    .deliveryMethod(null)
                    .deliveryFee(null)
                    .recipientAddress(orderRequest.getRecipientAddress())
                    .recipientPhoneNumber(null)
                    .deliveryFeeCondition(null)
                    .deliveryCompanyName("한진택배")
                    .deliveryCompanyContact(null)
                    .deliveryLocation(null)
                    .member(Member.builder().memberSeq(orderRequest.getMemberSeq()).build())
                    .product(Product.builder().productSeq(productSave.getProductSeq()).build())
                    .order(Order.builder().orderSeq(orderSave.getOrderSeq()).build())
                    .payment(Payment.builder().paymentSeq(paymentSave.getPaymentSeq()).build())
                    .build();
            Delivery save = deliveryRepository.save(delivery);

            // delivery 적재 후 .. 배송 status 변경 schedule..
            scheduledDeliveryTask = taskScheduler.scheduleAtFixedRate(() -> deliveryScheduled(save), Instant.now().plusSeconds(10), Duration.ofSeconds(10));


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
    public void stopDeliverySchedule() {
        // 해당 스케줄 종료..
        if (scheduledDeliveryTask != null) {
            scheduledDeliveryTask.cancel(false);
        }
    }

    @Async
    public void deliveryScheduled(Delivery delivery) {
        Optional<Delivery> byId = deliveryRepository.findById(delivery.getDeliverySeq());

        System.out.println(byId.get().getDeliverySeq() + " status 변경 진행중..");

        int currentStatus = byId.get().getDeliveryStatus();

        if (currentStatus < 4) {
            // deliveryStatus 값을 1씩 증가시킴
            byId.get().setDeliveryStatus(byId.get().getDeliveryStatus() + 1);
            deliveryRepository.save(byId.get());
        } else {
            // deliveryStatus 값이 4가 되었으므로 스케줄링 종료
            stopDeliverySchedule();
        }
    }


}

