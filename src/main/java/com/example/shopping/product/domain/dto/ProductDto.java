package com.example.shopping.product.domain.dto;

import com.example.shopping.inventory.domain.entity.Inventory;
import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.entity.ProductOption;
import com.example.shopping.product.domain.entity.ProductOption2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ProductDto {
    private Long productSeq;
    private String brand;
    private String hName;
    private String eName;
    private String imgUrl;
    private Integer price;
    private Integer options;

    public ProductDto(Product product) {
        this.productSeq = product.getProductSeq();
        this.hName = product.getHname();
        this.eName = product.getEname();
        this.imgUrl = product.getImgUrl();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.options = product.getProductOptions().get(0).getSize();

    }
}
