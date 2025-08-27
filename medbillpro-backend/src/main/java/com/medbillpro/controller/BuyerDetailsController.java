package com.medbillpro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medbillpro.constants.URLMapping;
import com.medbillpro.entity.BuyerDetails;
import com.medbillpro.service.BuyerDetailsService;

@RestController
@RequestMapping(URLMapping.BUYER_API_BASE)
public class BuyerDetailsController {

	@Autowired
	private BuyerDetailsService buyerDetailsService;

	@PostMapping(URLMapping.CREATE_BUYERDETAILS)
	public BuyerDetails saveBuyerDetails(@RequestBody BuyerDetails sellerDetails) {

		return buyerDetailsService.saveBuyerDetails(sellerDetails);
	}

	@GetMapping(URLMapping.GET_ALL_BUYERDETAILS)
	public List<BuyerDetails> getAllBuyerDetails() {
		// TODO Auto-generated method stub
		
		return buyerDetailsService.getAllBuyerDetails() ;
	}

	@PutMapping(URLMapping.UPDATE_BUYERDETAILS)
	public BuyerDetails updateBuyerDetails(@PathVariable Long id,@RequestBody BuyerDetails updatedSellerDetails) {
		
		return  buyerDetailsService.updateBuyerDetails(id, updatedSellerDetails);
	}

	@DeleteMapping(URLMapping.DELETE_BUYERDETAILS)
	public String deleteBuyerDetails(@PathVariable Long id) {
		boolean buyer= buyerDetailsService.deleteBuyerDetails(id);
		if(buyer)
		return "buyer is deleted scussefully.." ;
		else
			return "buyer is not exest..";
	}

	@GetMapping(URLMapping.GET_BUYERDETAILS_BY_ID)
	public Optional<BuyerDetails> getBuyerDetailsById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return buyerDetailsService.getBuyerDetailsById(id);
	}

}
