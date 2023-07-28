package com.example.shopping.product.domain.dto;

import com.example.shopping.product.domain.entity.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductOptionDto {
    private Long optionSeq;
    private String color;
    private Integer size;
//    private Long productSeq;

    public ProductOptionDto(ProductOption productOption) {
        this.optionSeq = productOption.getOptionSeq();
        this.color = productOption.getColor();
        this.size = productOption.getSize();
//        this.productSeq = productOption.getProduct().getProductSeq();
    }
}
