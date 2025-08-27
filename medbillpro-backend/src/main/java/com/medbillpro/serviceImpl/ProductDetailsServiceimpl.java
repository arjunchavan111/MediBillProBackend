package com.medbillpro.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medbillpro.entity.ProductDetails;
import com.medbillpro.repository.ProductDetailsRepository;
import com.medbillpro.service.ProductDetailsService;

@Service
public class ProductDetailsServiceimpl implements ProductDetailsService {

	@Autowired
    private  ProductDetailsRepository repository;

   

    // Create / Add New
    public ProductDetails addProduct(ProductDetails product) {
        return repository.save(product);
    }

    // Update
    public ProductDetails updateProduct(String productId, ProductDetails product) {
        Optional<ProductDetails> existing = repository.findById(productId);
        if (existing.isPresent()) {
            product.setProductId(productId); // keep same id
            return repository.save(product);
        }
        throw new RuntimeException("Product not found with ID: " + productId);
    }

    // Get All
    public List<ProductDetails> getAllProducts() {
        return repository.findAll();
    }

    // Get By Id
    public ProductDetails getProductById(String productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }

    // Delete
    public String deleteProduct(String productId) {
        if (repository.existsById(productId)) {
            repository.deleteById(productId);
            return "Product deleted with ID: " + productId;
        }
        throw new RuntimeException("Product not found with ID: " + productId);
    }
}
