package com.cg.productphotoshoppe.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.productphotoshoppe.exception.ResourceNotFoundException;
import com.cg.productphotoshoppe.model.Product;
import com.cg.productphotoshoppe.service.ProductService;

@RestController
public class ProductController {
	 private static final Logger logger = LogManager.getLogger(ProductController.class);

	@Autowired
	private ProductService productservice;

	@GetMapping("/products")
	public List<Product> getAllProducts(Pageable pageable) {
		logger.info("getAllProducts() method started");

		return (List<Product>) productservice.getAllProduct();
	}

	@GetMapping("/category/{categoryName}/products")
	public List<Product> getAllProductsByCategory(@PathVariable(value = "categoryName") String categoryName,
			Pageable pageable) {
		logger.info("getAllProductsByCategory() method started");

		return (List<Product>) productservice.getProductByCategory(categoryName);
	}

	@PostMapping("/category/{categoryName}/products")
	public Product createProduct(@PathVariable(value = "categoryName") String categoryName,
			@RequestBody Product product) throws ResourceNotFoundException {
		logger.info("createProduct() method started");

		return productservice.addProduct(categoryName, product);
	}

	@PutMapping("/category/{categoryName}/products/{productId}")
	public Product updateProduct(@PathVariable(value = "categoryName") String categoryName,
			@PathVariable(value = "productId") Long productId, @RequestBody Product productRequest)
			throws ResourceNotFoundException {
		logger.info("updateProduct() method started");

		return productservice.updateProduct(categoryName, productId, productRequest);
	}

	@DeleteMapping("/category/{categoryName}/products/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "categoryName") String categoryName,
			@PathVariable(value = "productId") Long productId) throws ResourceNotFoundException {
		logger.info("deleteProduct() method started");

		return productservice.deleteProduct(productId, categoryName);
	}
}
