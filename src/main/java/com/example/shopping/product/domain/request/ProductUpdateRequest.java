package com.example.shopping.product.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductUpdateRequest {
    private String name;
    private String img_url;
}
