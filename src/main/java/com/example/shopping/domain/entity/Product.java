package com.example.shopping.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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

    public void update(String name, String img_url) {
        this.name = name;
        this.img_url = img_url;
    }
}
