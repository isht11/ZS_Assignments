/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.services;

import com.zs.assignment11.dao.ProductDao;
import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.exceptions.InternalServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Product service that connects to product Dao which in turn connects to the database.
 */
@Service
public class ProductService {

    ProductDao productDao;
    private static final Logger logger = LogManager.getLogger(ProductService.class.getName());

    public ProductService(){
        productDao=new ProductDao();
    }

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
   }

    /**
     * Saves the product in the database.
     * @param product
     * @param category
     */
    public void saveProduct(Product product, Category category) {
        try {
            productDao.save(product, category);
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Returns a list of all the products in the database.
     * @return
     */
    public List<Product> getAllProducts() {
        List<Product> productList = null;
        try {
            productList = productDao.findAll();
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }
        return productList;
    }

    /**
     * Returns a list of all the products present in the respective category.
     * @param category
     * @return
     */
    public List<String> getAllProductByCategory(String category) {
        List<String> productList = null;
        try {
            productList = productDao.findAllInCategory(category);
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }
        return productList;
    }

    /**
     * Updates the product details in the table.
     * @param product
     * @param id
     */
    public void updateProduct(Product product, int id) {
        try {
            productDao.updateProduct(id,product);
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }

    }
}