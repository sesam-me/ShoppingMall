package com.example.shopping.product.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class ProductResponse2 {
    private Long productSeq;
    private String hName;
    private String eName;
    private String imgUrl;
    private String detailImgUrl;
    private LocalDate createAt;
    private String brand;
    private Integer price;
    private Integer size;
    private String color;
}
