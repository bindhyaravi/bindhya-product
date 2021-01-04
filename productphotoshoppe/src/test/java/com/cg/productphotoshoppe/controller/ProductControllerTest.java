package com.cg.productphotoshoppe.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.productphotoshoppe.model.Category;
import com.cg.productphotoshoppe.model.Product;
import com.cg.productphotoshoppe.repository.CategoryRepository;
import com.cg.productphotoshoppe.repository.ProductRepository;
import com.cg.productphotoshoppe.service.CategoryService;
import com.cg.productphotoshoppe.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	@MockBean
	private ProductRepository productRepository;
	
	@Test
	public void testGetAllProduct() throws Exception {
		String URI= "/products";
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
		
		String jsonInput = this.converttoJson(productList);
		Mockito.when(productService.getAllProduct()).thenReturn(productList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	@Test
	public void testGetProductByCategory() throws Exception {
		String URI= "/category/{categoryName}/products";
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
		
		String jsonInput = this.converttoJson(productList);
		Mockito.when(productService.getProductByCategory(Mockito.anyString())).thenReturn(productList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI,"cards").accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	private String converttoJson(Object product) throws JsonProcessingException {
		 ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(product);
	}
	@Test
	public void testCreateProduct() throws Exception {
		String URI= "/category/{categoryName}/products";
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
		String jsonInput = this.converttoJson(product);
		Mockito.when(productService.addProduct(Mockito.anyString(),Mockito.any(Product.class))).thenReturn(product);
		  
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI,"cards").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}
	@Test
	public void testUpdateProduct() throws Exception{
		String URI = "/category/{categoryName}/products/{productId}";
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
		String jsonInput = this.converttoJson(product);


        Mockito.when(productService.updateProduct(Mockito.anyString(),Mockito.anyLong(), Mockito.any())).thenReturn(product);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,"cards",8098l).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	

}
