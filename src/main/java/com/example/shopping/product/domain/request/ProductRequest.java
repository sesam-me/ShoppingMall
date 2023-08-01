package com.example.shopping.product.domain.request;

import com.example.shopping.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String hname;
    private String ename;
    private String imgUrl;
    private String detailImgUrl;
    private LocalDate createAt;
    private String brand;
    private Integer price;

    public Product ToEntity() {
        return Product.builder()
                .imgUrl(imgUrl)
                .hname(hname)
                .ename(ename)
                .detailImgUrl(detailImgUrl)
                .createAt(createAt)
                .brand(brand)
                .price(price)
                .build();
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "hName='" + hname + '\'' +
                ", eName='" + ename + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", detailImgUrl='" + detailImgUrl + '\'' +
                ", createAt=" + createAt +
                ", brand='" + brand + '\'' +
                '}';
    }
}
