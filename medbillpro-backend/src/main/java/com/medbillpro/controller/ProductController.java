package com.medbillpro.controller;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.entity.Product;
import com.medbillpro.entity.Purchase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLMapping.PRODUCT_API_BASE)
public class ProductController {

    @PostMapping(URLMapping.ADD_PURCHASE_DETAILS)
    public ResponseEntity<ApiResponse> savePurchase(@RequestBody Product purchase) {
        // Here you would typically save the purchase to the database
        // For now, we will just return a success response
        ApiResponse response = new ApiResponse(true, "Purchase details saved successfully", purchase);
        return ResponseEntity.ok(response);

    }
}
