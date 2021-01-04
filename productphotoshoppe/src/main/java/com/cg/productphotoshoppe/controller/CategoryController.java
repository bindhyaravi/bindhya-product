package com.cg.productphotoshoppe.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.productphotoshoppe.controller.CategoryController;
import com.cg.productphotoshoppe.exception.ResourceNotFoundException;
import com.cg.productphotoshoppe.model.Category;
import com.cg.productphotoshoppe.repository.CategoryRepository;
import com.cg.productphotoshoppe.service.CategoryService;

@RestController
@RequestMapping("/onlineShoppe")
public class CategoryController {

	 private static final Logger logger = LogManager.getLogger(CategoryController.class);

	@Autowired
	private CategoryRepository categoryrepository;

	@Autowired
	private CategoryService categoryservice;

	@GetMapping("/category")
	public List<Category> getAllCategory(Pageable pageable) {
		logger.info("getAllCategory() method started");
		return (List<Category>) categoryservice.getAllCategory();
	}

	@PostMapping("/category")
	public Category createCategory(@RequestBody Category category) {
		logger.info("createCategory() method started");
		return categoryservice.createCategory(category);
	}

	@DeleteMapping("/category/{categoryName}")
	public ResponseEntity<?> deleteCategory(@PathVariable String categoryName) throws ResourceNotFoundException {
		logger.info("deleteCategory() method started");
		return categoryservice.deleteCategory(categoryName);
	}

	@PutMapping("/category/{categoryName}")
	public Category updateCategory(@PathVariable String categoryName, @RequestBody Category category)
			throws ResourceNotFoundException {
		logger.info("updateCategory() method started");
		return categoryservice.updatecatgegory(categoryName, category);
	}
}
