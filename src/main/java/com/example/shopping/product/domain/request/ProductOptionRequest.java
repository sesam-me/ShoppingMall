package com.example.shopping.product.domain.request;

import com.example.shopping.product.domain.entity.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionRequest {
    private String color;
    private Integer size;

    public ProductOption toEntity() {
        return ProductOption
                .builder()
                .color(color)
                .size(size)
                .build();
    }
}
