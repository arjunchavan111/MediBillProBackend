package com.medbillpro.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.medbillpro.entity.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, String> {

    ProductDetails findByProductName(String productName);
}