package com.example.shopping.product.repository;

import com.example.shopping.product.domain.entity.ProductOption;
import com.example.shopping.product.domain.entity.ProductOption2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductOption2Repository extends JpaRepository<ProductOption2,Long> {
    Optional<ProductOption2> findById(Long optionSeq);
}
