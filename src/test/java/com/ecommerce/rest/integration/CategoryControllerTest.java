package com.ecommerce.rest.integration;

import com.ecommerce.rest.model.Category;
import com.ecommerce.rest.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testAddCategory() throws Exception {
        Category category = new Category("pasta");

        System.out.println(category);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category.getName())))
                .andExpect(status().isOk())
                .andReturn();

        int actualResponseBody = mvcResult.getResponse().getStatus();

        assertEquals(actualResponseBody, 200);

    }

    @Test
    public void testGetAllCategories() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/category/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(categoryService.getAllCategories());

        assertEquals(expectedResponseBody, actualResponseBody);
    }

    @Test
    public void testDeleteCategory() throws Exception {
        String name = "pasta";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/category/delete")
                        .param("name", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(categoryService.deleteCategory(name));

        assertEquals(expectedResponseBody, actualResponseBody);
    }
}
