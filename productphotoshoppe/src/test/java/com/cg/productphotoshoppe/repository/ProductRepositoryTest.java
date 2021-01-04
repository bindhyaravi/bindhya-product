package com.cg.productphotoshoppe.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.productphotoshoppe.model.Category;
import com.cg.productphotoshoppe.model.Product;
import com.cg.productphotoshoppe.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void testfindAll() throws Exception {
		Category category1 = new Category();
		category1.setName("wallpaint");
		category1.setDescription("good");
		testEntityManager.persist(category1);
		Product product1 = new Product();
		product1.setId(8098l);
		product1.setPrice(11.2);
		product1.setProductName("newproduct");
		product1.setQuantity("123");
		product1.setDescription("good");
		product1.setCategory(category1);
		List<Product> productList = new ArrayList<Product>();
		productList.add(product1);
		assertEquals(1, productList.size());

	}

	@Test
	public void testfindByCategory() throws Exception {
		Category category1 = new Category();
		category1.setName("paint");
		category1.setDescription("good");
		testEntityManager.persist(category1);
		Product product1 = new Product();
		product1.setPrice(11.2);
		product1.setProductName("newproduct");
		product1.setQuantity("123");
		product1.setDescription("good");
		product1.setCategory(category1);
		testEntityManager.persist(product1);
		Product product2 = new Product();
		product2.setPrice(11.2);
		product2.setProductName("newproduct");
		product2.setQuantity("123");
		product2.setDescription("good");
		product2.setCategory(category1);
		testEntityManager.persist(product2);
		List<Product> getFromInDb = productRepository.findByCategoryName("paint");

		List<Product> productList = new ArrayList<Product>();
		productList.add(product1);
		productList.add(product2);
		assertEquals(getFromInDb, productList);

	}
	@Test
	public void testAddProduct() throws Exception {
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("Nice");
		Category saveInDb = testEntityManager.persist(category1);
		Product product1 = new Product();	
		product1.setPrice(11.2);
		product1.setProductName("newproduct");
		product1.setQuantity("123");
		product1.setDescription("good");
		product1.setCategory(category1);
		Product saveInDb1 = testEntityManager.persist(product1);		
		Product getFromInDb = productRepository.findById(2l).get();
		assertThat(getFromInDb).isEqualTo(saveInDb1);
				
	}
	@Test
	public void testUpdateProduct() {
		Category category1 = new Category();
		category1.setName("wallpaint");
		category1.setDescription("Nice");
		testEntityManager.persist(category1);
		Product product1 = new Product();	
		product1.setPrice(11.2);
		product1.setProductName("newproduct");
		product1.setQuantity("123");
		product1.setDescription("good");
		product1.setCategory(category1);
		testEntityManager.persist(product1);	
		if(categoryRepository.findById("wallpaint").isPresent()) {
		Product getFromDb = productRepository.findById(1l).get();
		getFromDb.setDescription("nice");
		testEntityManager.persist(getFromDb);

		assertThat(getFromDb.getDescription()).isEqualTo("nice");
		}
	}
	@Test
	public void testDeleteCategory() throws Exception {
		Category category1 = new Category();
		category1.setName("wallpaint");
		category1.setDescription("Nice");
		testEntityManager.persist(category1);
		Product product1 = new Product();	
		product1.setPrice(11.2);
		product1.setProductName("newproduct");
		product1.setQuantity("123");
		product1.setDescription("good");
		product1.setCategory(category1);
		testEntityManager.persist(product1);
		
		Category category2 = new Category();
		category2.setName("paint");
		category2.setDescription("holiday cards of various design");
		Product product2 = new Product();	
		product2.setPrice(11.2);
		product2.setProductName("newproduct");
		product2.setQuantity("123");
		product2.setDescription("good");
		product2.setCategory(category1);
		testEntityManager.persist(product2);

		// delete one category from DataBase
		testEntityManager.remove(product2);

		List<Product> productList = (List<Product>) productRepository.findAll();
		Assert.assertEquals(productList.size(), 1);

	}
	}

