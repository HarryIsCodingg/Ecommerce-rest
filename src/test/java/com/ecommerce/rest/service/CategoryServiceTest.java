package com.ecommerce.rest.service;

import com.ecommerce.rest.model.Category;
import com.ecommerce.rest.repository.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category("fruit"); // Add your own initialization data
        Category category2 = new Category("vegetable"); // Add your own initialization data

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        // Print
        System.out.println(categories);
        System.out.println(result);
        // Print

        assertEquals(categories.size(), result.size());
        assertEquals(categories, result);
    }

    @Test
    public void testAddCategory() {
        Category category = new Category("dairy"); // Add your own initialization data

        when(categoryRepository.save(category)).thenReturn(category);

        boolean result = categoryService.addCategory(category);

        // Print
        System.out.println("It is " + result + " that the category has been added.");
        // Print

        assertTrue(result);
    }

    @Test
    public void testDeleteCategory() {
        String name = "poultry";
        Category category1 = new Category(name); // Add your own initialization data
        Category category2 = new Category("beef"); // Add your own initialization data
        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.deleteCategory(name);

        // Print
        System.out.println(categories);
        System.out.println(result);
        // Print

        assertEquals(categories.size(), result.size());
        assertEquals(categories, result);

        verify(categoryRepository).deleteCategoryByName(name);
    }
}