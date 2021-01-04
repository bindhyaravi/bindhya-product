package com.cg.productphotoshoppe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.productphotoshoppe.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{

}
