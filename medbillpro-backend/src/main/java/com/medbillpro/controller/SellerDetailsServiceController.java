package com.medbillpro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.entity.SellerDetails;
import com.medbillpro.service.SellerDetailsService;

@RestController
@RequestMapping(URLMapping.SELLER_API_BASE)
public class SellerDetailsServiceController {

    @Autowired
    private SellerDetailsService sellerDetailsService;

    @PostMapping(URLMapping.CREATE_SELLER)
    public ResponseEntity<ApiResponse> saveSellerDetails(@RequestBody SellerDetails invoiceItem) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            if (invoiceItem != null) {
                SellerDetails saved = sellerDetailsService.saveInviceItome(invoiceItem);
                apiResponse.setStatus(1);
                apiResponse.setMessage("Seller saved successfully.");
                apiResponse.setData(saved);
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Invalid seller details.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to save seller: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(URLMapping.GET_ALL_SELLER)
    public ResponseEntity<ApiResponse> getAllInviceItome() {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            List<SellerDetails> sellers = sellerDetailsService.getAllInviceItome();
            apiResponse.setStatus(1);
            apiResponse.setMessage("Sellers fetched successfully.");
            apiResponse.setData(sellers);
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to fetch sellers: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping(URLMapping.UPDATE_SELLER )
    public ResponseEntity<ApiResponse> updateSellerDetails(@PathVariable Long id,
                                                           @RequestBody SellerDetails updatedSellerDetails) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            SellerDetails updated = sellerDetailsService.updateSellerDetails(id, updatedSellerDetails);
            if (updated != null) {
                apiResponse.setStatus(1);
                apiResponse.setMessage("Seller updated successfully.");
                apiResponse.setData(updated);
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Seller not found or update failed.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to update seller: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping(URLMapping.DELETE_SELLER )
    public ResponseEntity<ApiResponse> deleteSellerDetails(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            String result = sellerDetailsService.deleteSellerDetails(id);
            apiResponse.setStatus(1);
            apiResponse.setMessage(result);
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to delete seller: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(URLMapping.GET_PRODUCT_BY_ID )
    public ResponseEntity<ApiResponse> getProductById(@PathVariable("id") Long id) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            Optional<SellerDetails> seller = sellerDetailsService.getSellerDetailsById(id);
            if (seller.isPresent()) {
                apiResponse.setStatus(1);
                apiResponse.setMessage("Seller fetched successfully.");
                apiResponse.setData(seller.get());
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Seller not found.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to fetch seller: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}