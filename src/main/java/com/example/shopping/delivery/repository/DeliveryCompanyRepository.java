package com.example.shopping.delivery.repository;

import com.example.shopping.delivery.domain.entity.DeliveryCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryCompanyRepository extends JpaRepository<DeliveryCompany, Long> {
}
