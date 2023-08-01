package com.ecommerce.rest.controller;

import com.ecommerce.rest.model.Category;
import com.ecommerce.rest.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("list")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
