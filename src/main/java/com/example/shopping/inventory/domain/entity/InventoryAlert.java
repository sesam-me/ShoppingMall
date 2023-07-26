package com.example.shopping.inventory.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="alerts")
public class InventoryAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertSeq;
    private Integer alertCount; // 재고 최소수량 밖에 남지 않았을 때 알림
}
