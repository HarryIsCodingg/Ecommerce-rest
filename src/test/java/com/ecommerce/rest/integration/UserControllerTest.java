package com.ecommerce.rest.integration;

import com.ecommerce.rest.model.Product;
import com.ecommerce.rest.model.User;
import com.ecommerce.rest.model.Credentials;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void Login_Endpoint_Returns_Valid_Credentials_WhenValidCredentials() throws Exception {

        Credentials credentials = new Credentials("harinder","user");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/login")
                        .content(mapper.writeValueAsString(credentials))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        User userReturned = mapper.readValue(mvcResult.getResponse().getContentAsString(),User.class);

        assertEquals(userReturned.getCredentials(),credentials);
    }

    @Test
    public void Login_Endpoint_Returns_Credentials_Null_WhenInValidCredentials() throws Exception {

        Credentials credentials = new Credentials("harinder","invalid");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/login")
                        .content(mapper.writeValueAsString(credentials))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        User userReturned = mapper.readValue(mvcResult.getResponse().getContentAsString(),User.class);

        assertNull(userReturned.getCredentials());
    }

    @Test
    public void Suggestions_Endpoint_Returns_ListOfSuggestions() throws Exception {

        List<String> productList = new ArrayList<>();
        productList.add("tomato");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/suggestions")
                        .content(mapper.writeValueAsString(productList))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Product> listOfSuggestions = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        listOfSuggestions.forEach(System.out::println);
        assertTrue(listOfSuggestions.size() > 0);
    }

    @Test
    public void Suggestions_Endpoint_Returns_BadRequest_When_ListNotProvided() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/suggestions"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void Coupons_Update_Endpoint_Returns_True_When_UserExists() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/coupons/update?coupons=4&username=test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean isCouponAdded = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertTrue(isCouponAdded);
    }

    @Test
    public void Coupons_Update_Endpoint_Returns_False_When_UserDoesntExists() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/coupons/update?coupons=4&username=invalid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean isCouponAdded = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertFalse(isCouponAdded);
    }
}
