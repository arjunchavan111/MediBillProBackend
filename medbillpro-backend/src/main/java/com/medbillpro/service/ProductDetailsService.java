package com.medbillpro.service;

import java.util.List;

import com.medbillpro.entity.ProductDetails;

public interface ProductDetailsService {

	public ProductDetails getProductById(String productId);

	public List<ProductDetails> getAllProducts();

	public String deleteProduct(String productId);

	public ProductDetails updateProduct(String productId, ProductDetails product);

	public ProductDetails addProduct(ProductDetails product);
	

}
