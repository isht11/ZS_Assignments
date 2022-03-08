/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.services;

import com.zs.assignment11.dao.CategoryDao;
import com.zs.assignment11.exceptions.InternalServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Connects to the dao class which connects to the table.
 */
@Service
public class CategoryService {

    CategoryDao categoryDao;
    private static final Logger logger  = LogManager.getLogger(CategoryService.class.getName());

    public CategoryService() {
        categoryDao=new CategoryDao();
    }

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao=categoryDao;
    }

    /**
     * Returns a list of all the categories.
     * @return
     */
    public List<String> getAll() {
        List<String> listCategories  = null;
        try {
            listCategories = categoryDao.getAll();
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }
        return listCategories;
    }
}
