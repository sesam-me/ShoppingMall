package com.example.shopping.product.domain.request;

import com.example.shopping.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String imgUrl;

    public Product ToEntity() {
        return Product.builder()
                .name(name)
                .imgUrl(imgUrl)
                .build();
    }
}
