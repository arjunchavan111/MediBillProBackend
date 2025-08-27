package com.medbillpro.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medbillpro.entity.GstInvoice;
import com.medbillpro.entity.InvoiceItem;
import com.medbillpro.entity.SellerDetails;
import com.medbillpro.repository.SellerDetailsRepository;
import com.medbillpro.service.SellerDetailsService;

@Service
public class SellerDetailsServiceImpl implements SellerDetailsService {

	@Autowired
	public SellerDetailsRepository sellerDetailsRepository;

	@Override
	public SellerDetails saveInviceItome(SellerDetails sellerDetails) {
		if (sellerDetails != null) {
			return sellerDetailsRepository.save(sellerDetails);
		} else {
			return sellerDetails;
		}
	}

	@Override
	public List<SellerDetails> getAllInviceItome() {

		return sellerDetailsRepository.findAll();
	}

	@Override
	public SellerDetails updateSellerDetails(Long id, SellerDetails updatedSellerDetails) {

		Optional<SellerDetails> existingSeller = sellerDetailsRepository.findById(id);

		if (existingSeller.isPresent()) {
			updatedSellerDetails.setSellerId(existingSeller.get().getSellerId());
			return sellerDetailsRepository.save(updatedSellerDetails);
		}

		return null;
	}

	@Override
	public String deleteSellerDetails(Long id) {
		sellerDetailsRepository.deleteById(id);

		return "data deleted successefully..";
	}

	@Override
	public Optional<SellerDetails> getSellerDetailsById(Long id) {
	Optional<SellerDetails> sellerDetails= sellerDetailsRepository.findById(id);
		return sellerDetails;
	}
}
