package com.ecommerce.rest.service;

import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.model.Product;
import com.ecommerce.rest.model.User;
import com.ecommerce.rest.repository.ProductRepository;
import com.ecommerce.rest.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidateUserLogin() {
        User user = new User();
        Credentials credentials = new Credentials();

        // Initialization Data
        credentials.setUsername("harinder");
        credentials.setPassword("right");
        user.setCredentials(credentials);
        // Initialization Data

        when(userRepository.findByCredentials_Username(credentials.getUsername())).thenReturn(user);

        User result = userService.validateUserLogin(credentials);

        // Print
        System.out.println(user);
        System.out.println(result);
        // Print

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void testValidateUserLoginInvalidCredentials() {
        Credentials invalidCredentials = new Credentials();

        // Initialization Data
        invalidCredentials.setUsername("harinder");
        invalidCredentials.setPassword("wrong");
        // Initialization Data

        when(userRepository.findByCredentials_Username(invalidCredentials.getUsername())).thenReturn(null);

        User result = userService.validateUserLogin(invalidCredentials);

        // Print
        System.out.println(result);
        // Print

        assertNotNull(result);
        // Assuming a new User() has null or default values for all its properties
        assertNull(result.getCredentials());
    }

    @Test
    public void testUpdateCoupons() {
        String username = "amitatest";
        int coupons = 5;
        User user = new User();

        // Initialization Data
        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword("dixit");

        user.setCoupons(coupons);
        user.setCredentials(credentials);
        user.setProductList(null);
        user.setHasCustomBasket(false);
        // Initialization Data

        when(userRepository.findByCredentials_Username(username)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        boolean result = userService.updateCoupons(coupons, username);

        System.out.println("It is "+result+" that "+username+"'s coupons have been updated.");

        assertTrue(result);
    }

}