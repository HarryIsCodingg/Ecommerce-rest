package com.ecommerce.rest.controller;

import com.ecommerce.rest.model.Admin;
import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.repository.AdminRepository;
import com.ecommerce.rest.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.net.http.HttpResponse;
import java.net.http.WebSocketHandshakeException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @MockBean
    private AdminRepository adminRepository;

    private ObjectMapper mapper = new ObjectMapper();
    private WebSocketHandshakeException mvcResult;

    @BeforeEach
    public void setup() {
        Admin admin = new Admin(); // Add your own initialization data
        Credentials credentials = new Credentials(); // Add your own initialization data

        // Initialization Data

        credentials.setUsername("harinder");
        credentials.setPassword("admin");
        admin.setCredentials(credentials);

        // Initialization Data

        Mockito.when(adminService.validateAdminLogin(credentials)).thenReturn(admin);
    }

    @Test
    public void testLoginValidation() throws Exception {
        Credentials credentials = new Credentials(); // Add your own initialization data

        // Initialization Data

        credentials.setUsername("harinder");
        credentials.setPassword("admin");

        // Initialization Data



        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/admin/login")
                        .content(mapper.writeValueAsString(credentials))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn(); // This will return the MvcResult;

        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testLoginValidationWithInvalidCredentials() throws Exception {
        Credentials invalidCredentials = new Credentials(); // Add your own initialization data
        Admin defaultAdmin = new Admin(); // Add your own initialization data

        // Initialization Data

        invalidCredentials.setUsername("harinder");
        invalidCredentials.setPassword("admin");
        defaultAdmin.setCredentials(invalidCredentials);

        // Initialization Data

        Mockito.when(adminService.validateAdminLogin(invalidCredentials)).thenReturn(defaultAdmin);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/admin/login")
                        .content(mapper.writeValueAsString(invalidCredentials))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // You can now get the response
        MockHttpServletResponse response = (MockHttpServletResponse) mvcResult.getResponse();

        // And print the HTTP status
        System.out.println("HTTP Status: " + response.getStatus());
        // Add assertions here based on your implementation


    }


}
