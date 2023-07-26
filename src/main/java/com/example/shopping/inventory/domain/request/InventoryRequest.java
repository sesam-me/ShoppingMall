package com.example.shopping.inventory.domain.request;

import com.example.shopping.inventory.domain.entity.Inventory;
import com.example.shopping.product.domain.entity.Product;
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

    public Inventory toEntity(Long productSeq) {
        return Inventory.builder()
                .count(count)
                .sales(sales)
                .waste(waste)
                .product(Product.builder().productSeq(productSeq).build())
                .build();
    }
}
