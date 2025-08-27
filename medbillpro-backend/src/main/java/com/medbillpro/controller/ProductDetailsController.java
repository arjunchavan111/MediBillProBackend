package com.medbillpro.controller;

import com.medbillpro.constants.URLMapping;
import com.medbillpro.entity.ProductDetails;
import com.medbillpro.service.ProductDetailsService;
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
    public ProductDetails addProduct(@RequestBody ProductDetails product) {
        return service.addProduct(product);
    }

    // Update Product
    @PutMapping(URLMapping.UPDATE_PRODUCT)
    public ProductDetails updateProduct(@PathVariable("id") String id,
                                        @RequestBody ProductDetails product) {
        return service.updateProduct(id, product);
    }

    // Get All Products
    @GetMapping(URLMapping.GET_ALL_PRODUCT)
    public List<ProductDetails> getAllProducts() {
        return service.getAllProducts();
    }

    // Get Product By Id
    @GetMapping(URLMapping.GET_PRODUCT_BY_ID)
    public ProductDetails getProductById(@PathVariable("id") String id) {
        return service.getProductById(id);
    }

    // Delete Product
    @DeleteMapping(URLMapping.DELETE_PRODUCT)
    public String deleteProduct(@PathVariable("id") String id) {
        return service.deleteProduct(id);
    }
}
