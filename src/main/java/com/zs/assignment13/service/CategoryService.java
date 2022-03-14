package com.zs.assignment13.service;

import com.zs.assignment13.dao.CategoryDao;
import com.zs.assignment13.entity.Category;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    CategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }

    public CategoryService(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    public List<Category> getAllCategories() throws InternalServerException {
        return categoryDao.getAllCategories();
    }

    public void addCategory(Category category) throws InternalServerException, NotFoundError, NotValidException {
        if(categoryDao.isExist(category.getId())){
            throw new NotFoundError("category already exist");
        }
        if (category.getId() < 0 || category.getName().isBlank() || category.getName() == null) {
            throw new NotValidException("The format is not valid");
        }
        categoryDao.addCategory(category);
    }
    public void deleteCategory(int id) throws InternalServerException, NotFoundError, NotValidException {
        if(!categoryDao.isExist(id)){
            throw new NotFoundError("category does not exist");
        }
        if (id < 0 ) {
            throw new NotValidException("The format is not valid");
        }
        categoryDao.deleteCategory(id);
    }
    public void updateCategory(int id,Category category) throws InternalServerException, NotFoundError, NotValidException {
        if(!categoryDao.isExist(id)){
            throw new NotFoundError("category does not exist");
        }
        if (category.getId() < 0 || category.getName().isBlank() || category.getName() == null) {
            throw new NotValidException("The format is not valid");
        }
        categoryDao.updateCategory(id,category);
    }
}
