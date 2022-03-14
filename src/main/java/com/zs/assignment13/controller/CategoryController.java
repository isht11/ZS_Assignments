package com.zs.assignment13.controller;

import com.zs.assignment13.entity.Category;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import com.zs.assignment13.service.CategoryService;
import com.zs.assignment13.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {


    CategoryService categoryService;
    private static final Logger logger = LogManager.getLogger(ProductService.class.getName());

    public CategoryController() {
        categoryService = new CategoryService();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllCategories() {
        List<Category> result = null;
        try {
            result = categoryService.getAllCategories();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            categoryService.addCategory(category);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        try {
            categoryService.updateCategory(category.getId(), category);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam int categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
