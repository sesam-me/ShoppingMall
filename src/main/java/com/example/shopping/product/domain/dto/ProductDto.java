package com.example.shopping.product.domain.dto;

import com.example.shopping.product.domain.entity.Product;
import lombok.Getter;

@Getter
public class ProductDto {
    private Long productSeq;
    private String name;
    private String brand;
    private String imgUrl;



    public ProductDto(Product product) {
        this.productSeq = product.getProductSeq();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.imgUrl = product.getImgUrl();

    }
}
