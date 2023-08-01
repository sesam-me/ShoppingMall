package com.example.shopping.inventory.domain.entity;

import com.example.shopping.product.domain.entity.Product;
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
    private Long inventorySeq;
    private Integer count; // 재고 수량
    private Integer sales; // 판매 수량
    private Integer waste; // 폐기 수량

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private Product product;

    public void update(Integer count, Integer sales, Integer waste) {
        this.count = count;
        this.sales = sales;
        this.waste = waste;
    }

    public void change(Integer quantity){
        if(this.count < quantity){
            throw new RuntimeException("너무 많이 시킴");
        }
        this.count-=quantity;
        this.sales+=quantity;
    }
}
