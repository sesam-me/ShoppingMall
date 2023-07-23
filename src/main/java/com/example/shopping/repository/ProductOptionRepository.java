package com.example.shopping.repository;

import com.example.shopping.domain.entity.Product;
import com.example.shopping.domain.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption,Long> {
    Optional<ProductOption> findById(Long option_seq);
}
