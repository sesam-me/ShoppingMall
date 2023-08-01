package com.example.shopping.product.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductUpdateRequest {
    private String hName;
    private String eName;
    private String imgUrl;
    private String detailImgUrl;
    private String brand;
    private Integer price;
}
