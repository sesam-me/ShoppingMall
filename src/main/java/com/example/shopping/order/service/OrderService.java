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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final PaymentRepository paymentRepository;
    private final DeliveryRepository deliveryRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final MemberRepository memberRepository;

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
        // 유저의 잔액을 조회 해야힘..
        // 잔액이 상품의 가격보다 낮다 ? return
        Payment save;
        Product product;
        try {
            orderRepository.save(orderRequest.toEntity(productSeq, memberSeq));
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
                    .product(Product.builder().productSeq(productSeq).build())
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
            product = productRepository.findById(productSeq)
                    .orElseThrow(()->new RuntimeException("We_don't_have_product"));

            Optional<Inventory> inventory = inventoryRepository.findByProduct(product);
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

        try {
            Payment payment = Payment.builder()
                    .paymentAmount(orderRequest.getPaymentAmount())
                    .paymentMethod(orderRequest.getPaymentMethod())
                    .paymentStatus(orderRequest.getPaymentStatus())
                    .cardType(orderRequest.getCardType())
                    .member(Member.builder().memberSeq(orderRequest.getMemberSeq()).build())
                    .paymentDate(orderRequest.getPaymentDate())
                    .build();

            save = paymentRepository.saveAndFlush(payment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }

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

        try {
            Member member = Member.builder().memberSeq(orderRequest.getMemberSeq()).build();

//            Order order = orderRepository.findByMember(member);
            List<Order> orders = orderRepository.findAllByMember(member);
            List<Delivery> deliveries = new ArrayList<>();

            for (Order order : orders) {
                // 이미 생성된 Delivery가 있는지 확인
                boolean isExistingDelivery = deliveries.stream()
                        .anyMatch(delivery -> delivery.getOrder().equals(order));

                if (!isExistingDelivery) {
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
                            .member(member)
                            .product(product)
                            .order(order)
                            .payment(save)
                            .build();

                    deliveries.add(delivery);
                }
            }

            // deliveries 리스트에 있는 Delivery들을 모두 저장
            for (Delivery delivery : deliveries) {
                deliveryRepository.save(delivery);
            }


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

