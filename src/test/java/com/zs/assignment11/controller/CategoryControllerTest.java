package com.zs.assignment11.controller;

import com.zs.assignment11.entity.Category;
import com.zs.assignment11.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryControllerTest {
    private CategoryController categoryController;
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        categoryService= mock(CategoryService.class);
        categoryController = new CategoryController(categoryService);
    }
    @Test
    void getAllCategories() {
        Category category = new Category(1, "electronics", 1);
        when(categoryService.getAll()).thenReturn(Arrays.asList(category.getCategoryName()));
        assertEquals(category.getCategoryName(), categoryController.getAllCategories().get(0));
    }
}