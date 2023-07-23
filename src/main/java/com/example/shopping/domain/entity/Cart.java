package com.example.shopping.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_seq;
    private Integer cart_count;

    public void update(Integer cart_count) {
        this.cart_count = cart_count;
    }
}
