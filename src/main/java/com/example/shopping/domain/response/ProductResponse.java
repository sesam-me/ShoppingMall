package com.example.shopping.domain.response;

import com.example.shopping.domain.entity.Product;

public class ProductResponse {
    private Long product_seq;
    private String name;
    private String img_url;

    public ProductResponse(Product product) {
        this.product_seq = product.getProduct_seq();
        this.name = product.getName();
        this.img_url = product.getImg_url();
    }
}
