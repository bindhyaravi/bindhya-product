package com.cg.productphotoshoppe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.productphotoshoppe.model.Product;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByIdAndCategoryName(Long id, String categoryName);

	List<Product> findByCategoryName(String categoryName);
}
