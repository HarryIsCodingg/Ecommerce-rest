package com.ecommerce.rest.integration;

import com.ecommerce.rest.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private Product product = null;

    @Test
    public void List_Endpoint_Returns_ListOfAllProducts() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/list"))
                .andExpect(status().isOk())
                .andReturn();

        List<Product> listOfProducts = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertTrue(listOfProducts.size() > 0);
    }

    @Test
    public void Save_Endpoint_Returns_True_WhenProductSaved() throws Exception {

        setup();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/save")
                        .content(mapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean isProductSaved = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertTrue(isProductSaved);
    }

    @Test
    public void Update_Endpoint_Returns_True_WhenProductUpdated() throws Exception {

       setup();
       product.setQuantity("50");


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/products/update?productToUpdate=garlic")
                        .content(mapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean isProductUpdated = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertTrue(isProductUpdated);
    }

    private void setup(){
        product = new Product();
        product.setName("garlic");
        product.setCategory("vegetables");
        product.setPricePerPound("4");
        product.setQuantity("43");
    }

}
