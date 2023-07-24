package com.example.shopping.inventory.domain.response;

import com.example.shopping.inventory.domain.entity.Inventory;

public class InventoryResponse {
    private Long inventory_seq;
    private Integer count; //재고 수량
    private Integer sales; // 판매 수량
    private Integer waste; // 폐기 수량

    public InventoryResponse(Inventory inventory) {
        this.count = inventory.getCount();
        this.sales = inventory.getSales();
        this.waste = inventory.getWaste();
    }
}
