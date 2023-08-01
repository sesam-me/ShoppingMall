package com.example.shopping.product.domain.dto;

import com.example.shopping.product.domain.entity.ProductOption;
import com.example.shopping.product.domain.entity.ProductOption2;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductOptionDto2 {
    private Long optionSeq;
    private String color;


    public ProductOptionDto2(ProductOption2 productOption2) {
        this.optionSeq = productOption2.getOptionSeq();
        this.color = productOption2.getColor();
    }

}
