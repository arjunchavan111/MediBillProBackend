package com.medbillpro.service;

import java.util.List;
import java.util.Optional;
import com.medbillpro.entity.BuyerDetails;

public interface BuyerDetailsService {

	public BuyerDetails saveBuyerDetails(BuyerDetails sellerDetails);

	public List<BuyerDetails> getAllBuyerDetails();

	public BuyerDetails updateBuyerDetails(Long id, BuyerDetails updatedSellerDetails);

	public boolean deleteBuyerDetails(Long id);

	public Optional<BuyerDetails> getBuyerDetailsById(Long id);

}
