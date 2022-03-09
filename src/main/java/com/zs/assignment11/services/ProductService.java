/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.services;

import com.zs.assignment11.dao.ProductDao;
import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.exceptions.InternalServerError;
import com.zs.assignment11.exceptions.ProductNotFoundError;
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
    public void saveProduct(Product product, Category category) throws InternalServerError, ProductNotFoundError {
        if(productDao.exist(product.getId())){
            throw new ProductNotFoundError("Product already exists");
        }
        productDao.save(product, category);
    }

    /**
     * Returns a list of all the products in the database.
     * @return
     */
    public List<Product> getAllProducts() throws InternalServerError {
        List<Product> productList = null;
        productList = productDao.findAll();
        return productList;
    }

    /**
     * Returns a list of all the products present in the respective category.
     * @param category
     * @return
     */
    public List<String> getAllProductByCategory(String category) throws InternalServerError {
        List<String> productList;
        productList = productDao.findAllInCategory(category);
        return productList;
    }

    /**
     * Updates the product details in the table.
     * @param product
     * @param id
     */
    public void updateProduct(Product product, int id) throws ProductNotFoundError, InternalServerError {
        if(!productDao.exist(product.getId())){
            throw new ProductNotFoundError("The product was not found in the database");
        }
        productDao.updateProduct(id,product);

    }
}