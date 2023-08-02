package com.ecommerce.rest.controller;

import com.ecommerce.rest.model.Category;
import com.ecommerce.rest.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("add")
    public boolean addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PostMapping("delete")
    public List<Category> deleteCategory(@RequestParam String name){
        return categoryService.deleteCategory(name);
    }
}
