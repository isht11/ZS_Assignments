/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.controller;

import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.exceptions.InternalServerError;
import com.zs.assignment11.exceptions.NotValidException;
import com.zs.assignment11.exceptions.ProductNotFoundError;
import com.zs.assignment11.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to control the product service class.
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Saves the product in the database.
     *
     * @param product
     * @param category
     * @return
     */
    @PostMapping("/ecommerce/product")
    public ResponseEntity<?> saveProduct(@RequestBody Product product, @RequestBody Category category) {
        try {
            productService.saveProduct(product, category);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (InternalServerError e) {
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (NotValidException | ProductNotFoundError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    /**
     * Gets all the products in the table.
     *
     * @return
     */
    @GetMapping("/ecommerce/products")
    public ResponseEntity<?> getAllProducts() {
        List<Product> productList = null;
        try {
            productList = productService.getAllProducts();
            return ResponseEntity.status(HttpStatus.OK).body(productList);
        } catch (InternalServerError e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Gets all the products present in the user given category.
     *
     * @param categoryName
     * @return
     */
    @GetMapping("/ecommerce/{categoryName}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String categoryName) {
        List<String> result = null;
        try {
            result = productService.getAllProductByCategory(categoryName);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (InternalServerError e) {
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    /**
     * Updates the product in the present in the table.
     *
     * @param product
     */
    @PutMapping("/ecommerce/update")
    public ResponseEntity<?> update(@RequestBody Product product) {

        try {
            productService.updateProduct(product, product.getId());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ProductNotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerError e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}