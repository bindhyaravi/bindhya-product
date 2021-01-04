package com.cg.productphotoshoppe.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.productphotoshoppe.exception.ResourceNotFoundException;
import com.cg.productphotoshoppe.model.Product;
import com.cg.productphotoshoppe.repository.CategoryRepository;
import com.cg.productphotoshoppe.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	@Autowired
	private CategoryRepository categoryrepository;

	@Autowired
	private ProductRepository productrepository;

	@Override
	public Product addProduct(String categoryName, Product product) throws ResourceNotFoundException {
		return categoryrepository.findById(categoryName).map(category -> {
			logger.info("addProduct() method in service started");
			product.setCategory(category);
			return productrepository.save(product);
		}).orElseThrow(() -> new ResourceNotFoundException(" and postId " + categoryName));
	}

	@Override
	public Iterable<Product> getAllProduct() {
		logger.info("getAllProduct() method in service started");
		return productrepository.findAll();
	}

	@Override
	public Iterable<Product> getProductByCategory(String categoryName) {
		logger.info("getProductByCategory() method in service started");
		return productrepository.findByCategoryName(categoryName);
	}

	@Override
	public Product updateProduct(String categoryName, Long productId, Product product)
			throws ResourceNotFoundException {
		logger.info("updateProduct() method in service started");
		if (!categoryrepository.existsById(categoryName)) {
			throw new ResourceNotFoundException("category" + categoryName + " not found");
		}
		return productrepository.findById(productId).map(p -> {
			p.setDescription(product.getDescription());
			p.setPrice(product.getPrice());
			p.setProductName(product.getProductName());
			p.setQuantity(product.getQuantity());
			return productrepository.save(p);
		}).orElseThrow(() -> new ResourceNotFoundException(
				"could not find id " + productId + " and category name " + categoryName));
	}

	@Override
	public ResponseEntity<?> deleteProduct(Long productId, String categoryName) throws ResourceNotFoundException {
		return productrepository.findByIdAndCategoryName(productId, categoryName).map(product -> {
			logger.info("deleteProduct() method in service started");
			productrepository.delete(product);
			return ResponseEntity.ok().build();
		}).orElseThrow(
				() -> new ResourceNotFoundException("could not find id " + productId + " and postId " + categoryName));
	}

}
