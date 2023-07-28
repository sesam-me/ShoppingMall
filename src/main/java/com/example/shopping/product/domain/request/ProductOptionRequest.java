package com.example.shopping.product.domain.request;

import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.entity.ProductOption;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOptionRequest {
    private String color;
    private Integer size;

    public ProductOption toEntity(Long productSeq) {
        return ProductOption
                .builder()
                .color(color)
                .size(size)
                .product(Product.builder().productSeq(productSeq).build())
                .build();
    }
}
