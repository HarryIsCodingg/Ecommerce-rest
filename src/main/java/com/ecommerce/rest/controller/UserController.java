package com.ecommerce.rest.controller;

import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.model.Product;
import com.ecommerce.rest.model.User;
import com.ecommerce.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @PostMapping("login")
    public User loginValidation(@RequestBody Credentials credentials){
        return userService.validateUserLogin(credentials);
    }

    @PostMapping("suggestions")
    public List<Product> getAllSuggestions(@RequestBody List<String> userProductNames){
        return userService.getProductsByUserUsername(userProductNames);
    }

    @PostMapping("coupons/update")
    public boolean updateCoupons(@RequestParam int coupons, String username ){
        return userService.updateCoupons(coupons, username);
    }
}
