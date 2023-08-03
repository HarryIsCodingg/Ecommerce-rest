package com.ecommerce.rest.service;

import com.ecommerce.rest.model.Admin;
import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.repository.AdminRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidateAdminLogin() {
        Admin admin = new Admin();
        Credentials credentials = new Credentials();

        // Initialization Data
        credentials.setUsername("harinder");
        credentials.setPassword("right");
        admin.setCredentials(credentials);
        // Initialization Data

        when(adminRepository.findByCredentials_Username(credentials.getUsername())).thenReturn(admin);

        Admin result = adminService.validateAdminLogin(credentials);

        // Print
        System.out.println(admin);
        System.out.println(result);
        // Print

        assertNotNull(result);
        assertEquals(admin, result);
    }

    @Test
    public void testValidateAdminLoginInvalidCredentials() {
        Credentials invalidCredentials = new Credentials();

        // Initialization Data
        invalidCredentials.setUsername("harinder");
        invalidCredentials.setPassword("wrong");
        // Initialization Data

        when(adminRepository.findByCredentials_Username(invalidCredentials.getUsername())).thenReturn(null);

        Admin result = adminService.validateAdminLogin(invalidCredentials);

        // Print
        System.out.println(result);
        // Print

        assertNotNull(result);
        // Assuming a new Admin() has null or default values for all its properties
        assertNull(result.getCredentials());
    }
}