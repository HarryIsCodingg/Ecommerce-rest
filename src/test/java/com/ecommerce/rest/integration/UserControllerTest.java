package com.ecommerce.rest.integration;

import com.ecommerce.rest.model.User;
import com.ecommerce.rest.model.Credentials;
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
import static org.junit.jupiter.api.Assertions.assertNull;
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
}
