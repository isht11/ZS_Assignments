/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.controller;

import com.zs.assignment11.exceptions.InternalServerError;
import com.zs.assignment11.services.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Category Controller to control the category service class.
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private static final Logger logger = LogManager.getLogger(CategoryController.class.getName());

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Returns a list of category names.
     *
     * @return
     */
    @GetMapping("/ecommerce/Categories")
    public ResponseEntity<?> getAllCategories() {
        List<String> categoryList = null;
        try {
            categoryList = categoryService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(categoryList);
        } catch (InternalServerError e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}