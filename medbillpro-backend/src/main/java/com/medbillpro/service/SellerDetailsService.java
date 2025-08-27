package com.medbillpro.service;

import java.util.List;
import java.util.Optional;

import com.medbillpro.entity.InvoiceItem;
import com.medbillpro.entity.SellerDetails;

public interface SellerDetailsService {

	public SellerDetails saveInviceItome(SellerDetails sellerDetails);

	public List<SellerDetails> getAllInviceItome();

	SellerDetails updateSellerDetails(Long id, SellerDetails updatedSellerDetails);

	String deleteSellerDetails(Long id);

	public Optional<SellerDetails> getSellerDetailsById(Long id);

}
