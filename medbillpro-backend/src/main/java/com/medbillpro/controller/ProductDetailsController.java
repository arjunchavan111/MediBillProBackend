package com.medbillpro.controller;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.entity.ProductDetails;
import com.medbillpro.service.ProductDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLMapping.PRODUCT_API_BASE)
public class ProductDetailsController {

    private final ProductDetailsService service;

    public ProductDetailsController(ProductDetailsService service) {
        this.service = service;
    }

    // Add New Product
    @PostMapping(URLMapping.CREATE_PRODUCT)
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDetails product) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            ProductDetails savedProduct = service.addProduct(product);
            apiResponse.setStatus(1);
            apiResponse.setMessage("Product added successfully.");
            apiResponse.setData(savedProduct);
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to add product: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Update Product
    @PutMapping(URLMapping.UPDATE_PRODUCT )
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") String id,
                                                     @RequestBody ProductDetails product) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            ProductDetails updatedProduct = service.updateProduct(id, product);
            if (updatedProduct != null) {
                apiResponse.setStatus(1);
                apiResponse.setMessage("Product updated successfully.");
                apiResponse.setData(updatedProduct);
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Product not found or update failed.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to update product: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Get All Products
    @GetMapping(URLMapping.GET_ALL_PRODUCT)
    public ResponseEntity<ApiResponse> getAllProducts() {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            List<ProductDetails> products = service.getAllProducts();
            apiResponse.setStatus(1);
            apiResponse.setMessage("Products fetched successfully.");
            apiResponse.setData(products);
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to fetch products: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Get Product By Id
    @GetMapping(URLMapping.GET_PRODUCT_BY_ID )
    public ResponseEntity<ApiResponse> getProductById(@PathVariable("id") String id) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            ProductDetails product = service.getProductById(id);
            if (product != null) {
                apiResponse.setStatus(1);
                apiResponse.setMessage("Product fetched successfully.");
                apiResponse.setData(product);
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Product not found.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to fetch product: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Delete Product
    @DeleteMapping(URLMapping.DELETE_PRODUCT)
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") String id) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            String result = service.deleteProduct(id);
            apiResponse.setStatus(1);
            apiResponse.setMessage(result);
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Failed to delete product: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}