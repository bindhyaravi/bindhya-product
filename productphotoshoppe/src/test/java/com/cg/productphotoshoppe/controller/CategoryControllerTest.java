package com.cg.productphotoshoppe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;

import com.cg.productphotoshoppe.model.Category;
import com.cg.productphotoshoppe.repository.CategoryRepository;
import com.cg.productphotoshoppe.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private CategoryService categoryService;
	@MockBean
	private CategoryRepository categoryRepository;
	
	@Test
	public void testGetAllCategory() throws Exception {
		String URI= "/onlineShoppe/category";
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
		
		Category category2 = new Category();
		category2.setName("frames");
		category2.setDescription("photo frames of your choice");

		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(category1);
		categoryList.add(category2);
		
		String jsonInput = this.converttoJson(categoryList);
		Mockito.when(categoryService.getAllCategory()).thenReturn(categoryList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);


	}
	
	@Test
	public void testCreateCategory() throws Exception {
		String URI= "/onlineShoppe/category";
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
		String jsonInput = this.converttoJson(category1);
		Mockito.when(categoryService.createCategory(Mockito.any(Category.class))).thenReturn(category1);
		  
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	private String converttoJson(Object category) throws JsonProcessingException {
		 ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(category);
	}
	
	@Test
	public void testUpdateCategory() throws Exception{
		String URI = "/onlineShoppe/category/{categoryName}";
		Category category1 = new Category();
		category1.setName("cards");
		category1.setDescription("holiday cards of various design");
        String jsonInput = this.converttoJson(category1);


        Mockito.when(categoryService.updatecatgegory(Mockito.anyString(), Mockito.any())).thenReturn(category1);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, "\"holiday cards of various design\"","cards").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
	}

}
