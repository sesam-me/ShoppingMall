package com.example.shopping.inventory.domain.dto;

import com.example.shopping.inventory.domain.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryDto {
    private Long inventorySeq;
    private Integer count; //재고 수량
    private Integer sales; // 판매 수량
    private Integer waste; // 폐기 수량
    private Long productSeq;

    public InventoryDto(Inventory inventory) {
        this.inventorySeq = inventory.getInventorySeq();
        this.count = inventory.getCount();
        this.sales = inventory.getSales();
        this.waste = inventory.getWaste();
        this.productSeq = inventory.getProduct().getProductSeq();
    }
}
