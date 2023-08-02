package com.ecommerce.rest.service;

import com.ecommerce.rest.model.Category;
import com.ecommerce.rest.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public boolean addCategory(Category category){
        return !StringUtils.isEmpty(categoryRepository.save(category));
    }

    public List<Category> deleteCategory(String name){

        categoryRepository.deleteCategoryByName(name);

        return categoryRepository.findAll();
    }
}
