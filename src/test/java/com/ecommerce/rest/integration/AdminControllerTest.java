package com.ecommerce.rest.integration;

import com.ecommerce.rest.model.Admin;
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
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void Login_Endpoint_Returns_Valid_Credentials_WhenValidCredentials() throws Exception {

        Credentials credentials = new Credentials("harinder","admin");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/admin/login")
                        .content(mapper.writeValueAsString(credentials))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Admin adminReturned = mapper.readValue(mvcResult.getResponse().getContentAsString(),Admin.class);

        assertEquals(adminReturned.getCredentials(),credentials);
    }

    @Test
    public void Login_Endpoint_Returns_Credentials_Null_WhenInValidCredentials() throws Exception {

        Credentials credentials = new Credentials("harinder","invalid");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/admin/login")
                        .content(mapper.writeValueAsString(credentials))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Admin adminReturned = mapper.readValue(mvcResult.getResponse().getContentAsString(),Admin.class);

        assertNull(adminReturned.getCredentials());
    }

}
