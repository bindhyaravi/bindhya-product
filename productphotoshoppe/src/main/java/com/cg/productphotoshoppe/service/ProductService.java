package com.cg.productphotoshoppe.service;

import org.springframework.http.ResponseEntity;

import com.cg.productphotoshoppe.exception.ResourceNotFoundException;
import com.cg.productphotoshoppe.model.Product;

public interface ProductService {
	Product addProduct(String categoryName, Product product) throws ResourceNotFoundException;

	Iterable<Product> getAllProduct();

	Iterable<Product> getProductByCategory(String categoryName);

	Product updateProduct(String categoryName, Long productId, Product product) throws ResourceNotFoundException;

	ResponseEntity<?> deleteProduct(Long productId, String categoryName) throws ResourceNotFoundException;
}
