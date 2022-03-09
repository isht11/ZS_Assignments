package com.zs.assignment11.services;

import com.zs.assignment11.dao.CategoryDao;
import com.zs.assignment11.entity.Category;
import com.zs.assignment11.exceptions.InternalServerError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    private CategoryService categoryService;
    private CategoryDao categoryDao;

    @BeforeEach
    void setup() {

        categoryDao = mock(CategoryDao.class);
        categoryService = new CategoryService(categoryDao);
    }
    @Test
    void getAll() throws InternalServerError {
        Category category = new Category(1, "electronics", 1);
        when(categoryDao.getAll()).thenReturn(Arrays.asList(category.getCategoryName()));
        assertEquals(category.getCategoryName(), categoryService.getAll().get(0));

    }
    @Test
    void testGetAllException() throws InternalServerError {
        doThrow(InternalServerError.class).when(categoryDao).getAll();
        assertThrows(InternalServerError.class, () -> categoryService.getAll());

    }
}