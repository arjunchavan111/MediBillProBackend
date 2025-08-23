package com.medbillpro.repository;

import com.medbillpro.entity.GstInvoice;
import com.medbillpro.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
}
