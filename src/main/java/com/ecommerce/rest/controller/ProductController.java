package com.ecommerce.rest.controller;

import com.ecommerce.rest.model.Product;
import com.ecommerce.rest.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@CrossOrigin
public class ProductController {

    private ProductService productService;

    @GetMapping("list")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
