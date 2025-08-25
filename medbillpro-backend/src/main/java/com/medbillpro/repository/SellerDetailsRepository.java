package com.medbillpro.repository;

import com.medbillpro.entity.ProductDetails;
import com.medbillpro.entity.SellerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerDetailsRepository extends JpaRepository<SellerDetails, String> {

    SellerDetails findBySellerName(String sellerName);

}