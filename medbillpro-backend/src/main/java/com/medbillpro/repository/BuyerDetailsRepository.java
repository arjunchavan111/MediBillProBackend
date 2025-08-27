package com.medbillpro.repository;

import com.medbillpro.entity.BuyerDetails;
import com.medbillpro.entity.SellerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerDetailsRepository extends JpaRepository<BuyerDetails, Long> {

    BuyerDetails findByBuyerName(String buyerName);

    
}

