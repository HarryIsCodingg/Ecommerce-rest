package com.ecommerce.rest.controller;

import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @PostMapping("login")
    public boolean loginValidation(@RequestBody Credentials credentials){
        return userService.validateAdminLogin(credentials);
    }
}
