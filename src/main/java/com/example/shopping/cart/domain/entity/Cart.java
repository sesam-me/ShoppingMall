package com.example.shopping.cart.domain.entity;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.product.domain.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @ManyToOne
    @JsonIgnore
    private Member member;

    @OneToMany(mappedBy = "carts", fetch = FetchType.LAZY)
    private List<Product> products;

    public void update(Integer cartCount) {
        this.cartCount = cartCount;
    }
}
