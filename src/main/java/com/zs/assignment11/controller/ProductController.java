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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param productId
     * @param productName
     * @param price
     * @param categoryId
     * @param categoryName
     */
    @PostMapping("/ecommerce/product")
    public void saveProduct(@RequestParam int productId, @RequestParam String productName, @RequestParam Float price, @RequestParam int categoryId, @RequestParam String categoryName) {
        Product product = new Product(productId, productName, price);
        Category category = new Category(categoryId, categoryName, productId);
        try {
            productService.saveProduct(product, category);
        } catch (InternalServerError | ProductNotFoundError | NotValidException e) {
            logger.error(e.getMessage());
        }
        logger.info("product saved successfully..!!");

    }

    /**
     * Gets all the products in the table.
     *
     * @return
     */
    @GetMapping("/ecommerce/products")
    public List<Product> getAllProducts() {
        List<Product> productList = null;
        try {
            productList = productService.getAllProducts();
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }
        logger.info("fetch all the product ");
        return productList;
    }

    /**
     * Gets all the products present in the user given category.
     *
     * @param categoryName
     * @return
     */
    @GetMapping("/ecommerce/{categoryName}")
    public List<String> getProductsByCategory(@PathVariable String categoryName) {
        List<String> result = null;
        try {
            result = productService.getAllProductByCategory(categoryName);
        } catch (InternalServerError | NotValidException e) {
            logger.error(e.getMessage());
        }
        logger.info("fetch all product by category");
        return result;
    }

    /**
     * Updates the product in the present in the table.
     *
     * @param productId
     * @param productName
     * @param price
     */
    @PutMapping("/ecommerce/update")
    public void update(@RequestParam int productId, @RequestParam String productName, @RequestParam Float price) {
        Product product = new Product(productId, productName, price);
        try {
            this.productService.updateProduct(product, product.getId());
        } catch (ProductNotFoundError | InternalServerError | NotValidException e) {
            logger.error(e.getMessage());
        }
        logger.info("product updated successfully ..!!");
    }
}