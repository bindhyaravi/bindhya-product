package com.cg.productphotoshoppe.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.productphotoshoppe.exception.ResourceNotFoundException;
import com.cg.productphotoshoppe.model.Category;

public interface CategoryService {
	List<Category> getAllCategory();

	Category updatecatgegory(String categoryName, Category category) throws ResourceNotFoundException;

	ResponseEntity<?> deleteCategory(String categoryName) throws ResourceNotFoundException;

	Category createCategory(Category category);
}
