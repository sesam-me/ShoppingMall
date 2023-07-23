package com.example.shopping.domain.request;

import com.example.shopping.domain.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private Integer count; //재고 수량
    private Integer sales; // 판매 수량
    private Integer waste; // 폐기 수량

    public Inventory toEntity() {
        return Inventory.builder()
                .count(count)
                .sales(sales)
                .waste(waste)
                .build();
    }
}
