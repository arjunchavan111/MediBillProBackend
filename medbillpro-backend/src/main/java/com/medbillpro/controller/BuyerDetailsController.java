package com.medbillpro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.entity.BuyerDetails;
import com.medbillpro.service.BuyerDetailsService;

@RestController
@RequestMapping(URLMapping.BUYER_API_BASE)
public class BuyerDetailsController {

    @Autowired
    private BuyerDetailsService buyerDetailsService;

    @PostMapping(URLMapping.CREATE_BUYERDETAILS)
    public ApiResponse saveBuyerDetails(@RequestBody BuyerDetails buyerDetails) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            BuyerDetails res = buyerDetailsService.saveBuyerDetails(buyerDetails);
            if (res != null) {
                apiResponse.setStatus(1);
                apiResponse.setMessage("Buyer saved successfully.");
                apiResponse.setData(res);
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Failed to save buyer.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Exception occurred: " + e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping(URLMapping.GET_ALL_BUYERDETAILS)
    public ApiResponse getAllBuyerDetails() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<BuyerDetails> buyers = buyerDetailsService.getAllBuyerDetails();
            apiResponse.setStatus(1);
            apiResponse.setMessage("Buyers fetched successfully.");
            apiResponse.setData(buyers);
            
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Exception occurred: " + e.getMessage());
        }
        return apiResponse;
    }

    @PutMapping(URLMapping.UPDATE_BUYERDETAILS )
    public ApiResponse updateBuyerDetails(@PathVariable Long id, @RequestBody BuyerDetails updatedBuyerDetails) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            BuyerDetails updated = buyerDetailsService.updateBuyerDetails(id, updatedBuyerDetails);
            if (updated != null) {
                apiResponse.setStatus(1);
                apiResponse.setMessage("Buyer updated successfully.");
                apiResponse.setData(updated);
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Buyer not found or update failed.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Exception occurred: " + e.getMessage());
        }
        return apiResponse;
    }

    @DeleteMapping(URLMapping.DELETE_BUYERDETAILS )
    public ApiResponse deleteBuyerDetails(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            boolean deleted = buyerDetailsService.deleteBuyerDetails(id);
            if (deleted) {
                apiResponse.setStatus(1);
                apiResponse.setMessage("Buyer deleted successfully.");
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Buyer not found.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Exception occurred: " + e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping(URLMapping.GET_BUYERDETAILS_BY_ID)
    public ApiResponse getBuyerDetailsById(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Optional<BuyerDetails> buyer = buyerDetailsService.getBuyerDetailsById(id);
            if (buyer.isPresent()) {
                apiResponse.setStatus(1);
                apiResponse.setMessage("Buyer fetched successfully.");
                apiResponse.setData(buyer.get());
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Buyer not found.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Exception occurred: " + e.getMessage());
        }
        return apiResponse;
    }
}