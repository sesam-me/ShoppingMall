package com.example.shopping.product.service;

import com.example.shopping.product.domain.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> byKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            String keywordLower = keyword.toLowerCase();
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("hname")), "%" + keywordLower + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("ename")), "%" + keywordLower + "%")
            );
        };
    }
}