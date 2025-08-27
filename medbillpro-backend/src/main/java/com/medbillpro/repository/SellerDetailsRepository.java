package com.medbillpro.repository;


import com.medbillpro.entity.SellerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerDetailsRepository extends JpaRepository<SellerDetails, Long> {

    SellerDetails findBySellerName(String sellerName);

}