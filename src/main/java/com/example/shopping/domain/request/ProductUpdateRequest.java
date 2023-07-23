package com.example.shopping.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductUpdateRequest {
    private String name;
    private String img_url;
}
