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
    private Long productSeq;
    private String name;
    private String imgUrl;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOption> productOption;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Interest> interests;

    public void update(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }
}
