package com.medbillpro.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medbillpro.entity.BuyerDetails;
import com.medbillpro.repository.BuyerDetailsRepository;
import com.medbillpro.service.BuyerDetailsService;

@Service
public class BuyerDetailsServiceImpl implements BuyerDetailsService {

	@Autowired
	public BuyerDetailsRepository buyerDetailsRepository;

	@Override
	public BuyerDetails saveBuyerDetails(BuyerDetails sellerDetails) {

		return buyerDetailsRepository.save(sellerDetails);
	}

	@Override
	public List<BuyerDetails> getAllBuyerDetails() {

		return buyerDetailsRepository.findAll();
	}

	@Override
	public BuyerDetails updateBuyerDetails(Long id, BuyerDetails updatedSellerDetails) {

		Optional<BuyerDetails> existing = buyerDetailsRepository.findById(id);
	
		 if (existing.isPresent()) {
			 updatedSellerDetails.setBuyerId(id); // keep same id
	            return buyerDetailsRepository.save(updatedSellerDetails);
		 }
		  throw new RuntimeException("Product not found with ID: " + id);
	}

	@Override
	public boolean deleteBuyerDetails(Long id) {
		// TODO Auto-generated method stub
		Optional<BuyerDetails> buyerid = buyerDetailsRepository.findById(id);
		if (buyerid != null) {
			buyerDetailsRepository.deleteById(id);
			return true;
		} else
			return false;
	}

	@Override
	public Optional<BuyerDetails> getBuyerDetailsById(Long id) {
		// TODO Auto-generated method stub
		return buyerDetailsRepository.findById(id);
	}

}
