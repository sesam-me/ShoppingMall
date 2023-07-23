package com.example.shopping.domain.request;

import com.example.shopping.domain.entity.Product;
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
    private String img_url;

    public Product ToEntity() {
        return Product.builder()
                .name(name)
                .img_url(img_url)
                .build();
    }
}
