package com.medbillpro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medbillpro.entity.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    ProductDetails findByProductName(String productName);
}