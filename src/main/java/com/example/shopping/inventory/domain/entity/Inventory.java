package com.example.shopping.inventory.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventory_seq;
    private Integer count; //재고 수량
    private Integer sales; // 판매 수량
    private Integer waste; // 폐기 수량

    public void update(Integer count, Integer sales, Integer waste) {
        this.count = count;
        this.sales = sales;
        this.waste = waste;
    }
}
