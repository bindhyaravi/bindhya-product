package com.cg.productphotoshoppe.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import com.cg.productphotoshoppe.model.Category;

@RunWith(SpringRunner.class)
@DataJpaTest

public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private TestEntityManager testEntityManager;

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

		testEntityManager.persist(category1);
		testEntityManager.persist(category2);

		List<Category> list = categoryRepository.findAll();
		assertEquals(2, categoryList.size());
	}

	@Test
	public void testCreateCategory() throws Exception {
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
		Category saveInDb = testEntityManager.persist(category1);
		Category getFromInDb = categoryRepository.findById("cards").get();
		assertThat(getFromInDb).isEqualTo(saveInDb);
	}

	@Test
	public void testUpdateCategory() {
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
		testEntityManager.persist(category1);

		Category getFromDb = categoryRepository.findById("cards").get();
		getFromDb.setDescription("good");
		testEntityManager.persist(getFromDb);

		assertThat(getFromDb.getDescription()).isEqualTo("good");
	}

	@Test
	public void testDeleteCategory() throws Exception {
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");

		Category category2 = new Category();
		category2.setName("paint");
		category2.setDescription("holiday cards of various design");

		testEntityManager.persist(category1);
		testEntityManager.persist(category2);

		// delete one category from DataBase
		testEntityManager.remove(category1);

		List<Category> categoryList = (List<Category>) categoryRepository.findAll();
		Assert.assertEquals(categoryList.size(), 1);

	}
}
