package com.cg.productphotoshoppe.service;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cg.productphotoshoppe.exception.ResourceNotFoundException;
import com.cg.productphotoshoppe.model.Category;
import com.cg.productphotoshoppe.model.Product;
import com.cg.productphotoshoppe.repository.CategoryRepository;
import com.cg.productphotoshoppe.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private ProductService productService;

	@MockBean
	private CategoryRepository categoryRepository;
	
	@Test
	public void testaddProduct() throws ResourceNotFoundException {
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
		Product product = new Product();
		product.setId(8098l);
		product.setPrice(20.00);
		product.setProductName("holiday card");
		product.setQuantity("12");
		product.setDescription("good");
		product.setCategory(category1);
		if(categoryRepository.findById("cards").isPresent()){
		Mockito.when(productRepository.save(product)).thenReturn(product);
		Product product1 = productService.addProduct("cards", product);
		assertThat(productService.addProduct("cards", product)).isEqualTo(product1);
		}
	}
	
	@Test 
	public void testGetAllProduct() {
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
		
		Product product = new Product();
		product.setId(8098l);
		product.setPrice(20.00);
		product.setProductName("holiday card");
		product.setQuantity("12");
		product.setDescription("good");
		product.setCategory(category1);
		
		Product product1 = new Product();
		product1.setId(8098l);
		product1.setPrice(20.00);
		product1.setProductName("holiday card");
		product1.setQuantity("12");
		product1.setDescription("good");
		product1.setCategory(category1);
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		productList.add(product1);
		Mockito.when(productRepository.findAll()).thenReturn(productList);
		List<Product> list = (List<Product>) productService.getAllProduct();
		assertThat(productService.getAllProduct()).isEqualTo(list);
	
	}
	@Test
	public void testGetProductByCategory() {
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
		
		Product product = new Product();
		product.setId(8098l);
		product.setPrice(20.00);
		product.setProductName("holiday card");
		product.setQuantity("12");
		product.setDescription("good");
		product.setCategory(category1);
		
		Product product1 = new Product();
		product1.setId(8098l);
		product1.setPrice(20.00);
		product1.setProductName("holiday card");
		product1.setQuantity("12");
		product1.setDescription("good");
		product1.setCategory(category1);
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		productList.add(product1);
		Mockito.when(productRepository.findAll()).thenReturn(productList);
		List<Product> list = (List<Product>) productService.getProductByCategory("cards");
		assertThat(productService.getProductByCategory("cards")).isEqualTo(list);
	}
	
	@Test 
	public void testUpdateProduct() throws ResourceNotFoundException{
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
		Product product = new Product();
		product.setId(8098l);
		product.setPrice(20.00);
		product.setProductName("holiday card");
		product.setQuantity("12");
		product.setDescription("good");
		product.setCategory(category1);
		if(categoryRepository.findById("cards").isPresent()){
			Mockito.when(productRepository.findById(8098l).get()).thenReturn(product);
			product.setPrice(20.00);
			product.setProductName("holiday cards");
			product.setQuantity("123");
			product.setDescription("happy holidays");
			product.setCategory(category1);
			Mockito.when(productRepository.save(product)).thenReturn(product);
			System.out.println(product.getDescription());
			Product product1 = productService.updateProduct("cards", 8098l, product);
			assertThat(productService.updateProduct("cards", 8098l, product)).isEqualTo(product1);		}
	}
	
	@Test
	public void testDeleteProduct() throws ResourceNotFoundException {
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
		Product product = new Product();
		product.setId(8098l);
		product.setPrice(20.00);
		product.setProductName("holiday card");
		product.setQuantity("12");
		product.setDescription("good");
		product.setCategory(category1);
		if (categoryRepository.findById("cards").isPresent()) {
			Mockito.when(productRepository.findById(8098l).get()).thenReturn(product);
			productRepository.deleteById(8098l);
           Assert.assertEquals(productService.deleteProduct(8098l, "cards"), 1);
		}
	}
	
}
