package com.zs.assignment13.controller;

import com.zs.assignment13.entity.Product;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import com.zs.assignment13.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductService.class.getName());
    ProductService productService;

    public ProductController() {
        productService = new ProductService();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllProducts() {
        List<Product> result = null;
        try {
            result = productService.getAllProduct();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {

        try {
            productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());

        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            productService.updateProduct(product.getId(), product);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @GetMapping("/get/category")
    public ResponseEntity<?> getWithCategory(@RequestParam String name) {
        List<String> result = null;
        try {
            result = productService.getAllProductWithCategory(name);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam int productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }


}
