package com.example.shopping.cart.domain.entity;

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
    private Long cartSeq;
    private Integer cartCount;

    public void update(Integer cartCount) {
        this.cartCount = cartCount;
    }
}
