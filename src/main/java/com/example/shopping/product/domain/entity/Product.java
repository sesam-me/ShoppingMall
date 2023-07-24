package com.example.shopping.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_seq;
    private String name;
    private String img_url;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOption> productOption;

    public void update(String name, String img_url) {
        this.name = name;
        this.img_url = img_url;
    }
}
