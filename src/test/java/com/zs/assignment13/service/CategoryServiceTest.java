package com.zs.assignment13.service;

import com.zs.assignment13.dao.CategoryDao;
import com.zs.assignment13.entity.Category;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    public CategoryService categoryService;
    private CategoryDao categoryDao;

    @BeforeEach
    void setup() {

        categoryDao = mock(CategoryDao.class);
        categoryService = new CategoryService(categoryDao);
    }

    @Test
    void getAllCategories() throws InternalServerException {
        categoryService.getAllCategories();
        verify(categoryDao, times(1)).getAllCategories();
    }

    @Test
    void addCategory() throws InternalServerException, NotFoundError, NotValidException {
        Category category = new Category(1, "Electronics");
        when(categoryDao.isExist(category.getId())).thenReturn(false);
        categoryService.addCategory(category);
        verify(categoryDao, times(1)).addCategory(category);
    }

    @Test
    void deleteCategory() throws InternalServerException, NotFoundError, NotValidException {
        int categoryId = 1;
        when(categoryDao.isExist(categoryId)).thenReturn(true);
        categoryService.deleteCategory(categoryId);
        verify(categoryDao, times(1)).deleteCategory(categoryId);

    }

    @Test
    void updateCategory() throws InternalServerException, NotFoundError, NotValidException {
        Category category = new Category(1, "electronics");
        when(categoryDao.isExist(category.getId())).thenReturn(true);
        categoryService.updateCategory(category.getId(), category);
        verify(categoryDao, times(1)).updateCategory(category.getId(), category);

    }

    @Test
    void testUpdateCategoryException() throws InternalServerException{
        Category category = new Category(1, "electronics");
        when(categoryDao.isExist(category.getId())).thenReturn(true);
        doThrow(InternalServerException.class).when(categoryDao).updateCategory(category.getId(), category);
        assertThrows(InternalServerException.class, () -> categoryService.updateCategory(category.getId(), category));

    }
    @Test
    void testAddCategoryException() throws InternalServerException{
        Category category = new Category(1, "electronics");
        doThrow(InternalServerException.class).when(categoryDao).addCategory(category);
        assertThrows(InternalServerException.class, () -> categoryService.addCategory(category));

    }
    @Test
    void testRemoveCategoryException() throws InternalServerException{
        Category category = new Category(1, "electronics");
        when(categoryDao.isExist(category.getId())).thenReturn(true);
        doThrow(InternalServerException.class).when(categoryDao).deleteCategory(category.getId());
        assertThrows(InternalServerException.class, () -> categoryService.deleteCategory(category.getId()));
    }
    @Test
    void testGetAllCategoryException() throws InternalServerException{
        doThrow(InternalServerException.class).when(categoryDao).getAllCategories();
        assertThrows(InternalServerException.class, () -> categoryService.getAllCategories());
    }
}