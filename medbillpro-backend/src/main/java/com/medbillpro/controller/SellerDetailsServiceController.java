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
import com.medbillpro.entity.ProductDetails;
import com.medbillpro.entity.SellerDetails;
import com.medbillpro.service.SellerDetailsService;

@RestController
@RequestMapping(URLMapping.SELLER_API_BASE)
public class SellerDetailsServiceController {

	@Autowired
	private SellerDetailsService sellerDetailsService;

	@PostMapping(URLMapping.CREATE_SELLER)
	public SellerDetails saveSellerDetails(@RequestBody SellerDetails invoiceItem) {
		if (invoiceItem != null) {
			return sellerDetailsService.saveInviceItome(invoiceItem);
		} else {
			return invoiceItem;
		}
	}

	@GetMapping(URLMapping.GET_ALL_SELLER)
	public List<SellerDetails> getAllInviceItome() {

		return sellerDetailsService.getAllInviceItome();
	}

	@PutMapping(URLMapping.UPDATE_SELLER)
	public SellerDetails updateSellerDetails(@PathVariable Long id, @RequestBody SellerDetails updatedSellerDetails) {

		return sellerDetailsService.updateSellerDetails(id, updatedSellerDetails);
	}

	@DeleteMapping(URLMapping.DELETE_SELLER)
	public String deleteSellerDetails(@PathVariable Long id) {

		return sellerDetailsService.deleteSellerDetails(id);
	}
	 // Get Product By Id
    @GetMapping(URLMapping.GET_PRODUCT_BY_ID)
    public Optional<SellerDetails> getProductById(@PathVariable("id") Long id) {
        return sellerDetailsService.getSellerDetailsById(id);
    }
}
