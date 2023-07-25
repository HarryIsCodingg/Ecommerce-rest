package com.ecommerce.rest.controller;

import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("admin")
public class AdminController {

    private AdminService adminService;

    @PostMapping("login")
    public boolean loginValidation(@RequestBody Credentials credentials){
        return adminService.validateAdminLogin(credentials);
    }
}
