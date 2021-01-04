package com.cg.productphotoshoppe.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.junit.Assert;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.productphotoshoppe.exception.ResourceNotFoundException;
import com.cg.productphotoshoppe.model.Category;
import com.cg.productphotoshoppe.repository.CategoryRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@MockBean
	private CategoryRepository categoryRepository;

	@MockBean
	private CategoryService categoryService;

	@Test
	public void testGetAllCategory() {
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");

		Category category2 = new Category();
		category2.setName("frames");
		category2.setDescription("photo frames of your choice");

		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(category1);
		categoryList.add(category2);

		Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
		List<Category> list = categoryRepository.findAll();
		assertEquals(2, categoryList.size());
	}

	@Test
	public void testCreateCategory() {
		Category category = new Category();
		category.setName("cards");
		category.setDescription("holiday cards of various design");
		Mockito.when(categoryRepository.save(category)).thenReturn(category);
		Category category1 = categoryService.createCategory(category);
		assertThat(categoryService.createCategory(category)).isEqualTo(category1);
	}

	@Test
	public void testUpdatecatgegory() throws ResourceNotFoundException {
		Category category = new Category();
		category.setName("cards");
		category.setDescription("holiday cards of various design");
		if (categoryRepository.findById("cards").isPresent()) {

			Mockito.when(categoryRepository.findById("cards").get()).thenReturn(category);
			category.setDescription("happy holidays");
			Mockito.when(categoryRepository.save(category)).thenReturn(category);
			System.out.println(category.getDescription());
			Category category1 = categoryService.updatecatgegory("cards", category);
			assertThat(categoryService.updatecatgegory("cards", category)).isEqualTo(category1);
		}
	}
	
	@Test
	public void testDeleteCategory() throws ResourceNotFoundException {
		Category category = new Category();
		category.setName("cards");
		category.setDescription("holiday cards of various design");
		if (categoryRepository.findById("cards").isPresent()) {
			Mockito.when(categoryRepository.findById("cards").get()).thenReturn(category);
           categoryRepository.deleteById("cards");
           Assert.assertEquals(categoryService.deleteCategory("cards"), 1);

		}
	}

}
