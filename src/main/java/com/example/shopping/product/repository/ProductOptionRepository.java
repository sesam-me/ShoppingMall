package com.example.shopping.product.repository;

import com.example.shopping.product.domain.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption,Long> {
    Optional<ProductOption> findById(Long optionSeq);
}
