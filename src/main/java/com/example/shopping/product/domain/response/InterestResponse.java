package com.example.shopping.product.domain.response;

import com.example.shopping.product.domain.dto.InterestDto;
import com.example.shopping.product.domain.dto.ProductDto;
import com.example.shopping.product.domain.entity.Interest;
import lombok.*;

import java.time.LocalDate;
@Getter
public class InterestResponse  extends InterestDto {
    private ProductDto product;


    public InterestResponse(Interest interest) {
        super(interest);
        product = new ProductDto(interest.getProduct());
    }
}
