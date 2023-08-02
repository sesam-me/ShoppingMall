package com.example.shopping.product.repository;

import com.example.shopping.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findById(Long productSeq);

    //@Query("select p from Product p where p.hname like '%productName%' OR p.ename LIKE '%productName%'")
    @Query("SELECT p FROM Product p WHERE p.hname LIKE %:productName% OR p.ename LIKE %:productName%")
    List<Product> searchProductByHnameAndEname(String productName);

}
