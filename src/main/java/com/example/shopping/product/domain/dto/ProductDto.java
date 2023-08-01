package com.example.shopping.product.domain.dto;

import com.example.shopping.product.domain.entity.Product;
import lombok.Getter;

@Getter
public class ProductDto {
    private Long productSeq;
    private String hName;
    private String eName;
    private String imgUrl;

    public ProductDto(Product product) {
        this.productSeq = product.getProductSeq();
        this.hName = product.getHname();
        this.eName = product.getEname();
        this.imgUrl = product.getImgUrl();
    }
}
