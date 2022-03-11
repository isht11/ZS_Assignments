/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.services;

import com.zs.assignment11.dao.ProductDao;
import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.exceptions.InternalServerError;
import com.zs.assignment11.exceptions.NotValidException;
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
    public void saveProduct(Product product, Category category) throws InternalServerError, ProductNotFoundError, NotValidException {
        if(productDao.exist(product.getId())){
            throw new ProductNotFoundError("Product already exists");
        }
        if (product.getId() < 0 || product.getProductName().isBlank() || product.getProductName() == null) {
            throw new NotValidException("The format is not valid");
        }
        productDao.save(product, category);
    }

    /**
     * Returns a list of all the products in the database.
     * @return
     */
    public List<Product> getAllProducts() throws InternalServerError {
        List<Product> productList;
        productList = productDao.findAll();
        return productList;
    }

    /**
     * Returns a list of all the products present in the respective category.
     * @param category
     * @return
     */
    public List<String> getAllProductByCategory(String category) throws InternalServerError, NotValidException {
        List<String> productList;
        productList = productDao.findAllInCategory(category);
        if (category.isBlank()) {
            throw new NotValidException("The format is not valid");
        }
        return productList;
    }

    /**
     * Updates the product details in the table.
     * @param product
     * @param id
     */
    public void updateProduct(Product product, int id) throws ProductNotFoundError, InternalServerError, NotValidException {
        if(!productDao.exist(product.getId())){
            throw new ProductNotFoundError("The product was not found in the database");
        }
        if (product.getId() < 0 || product.getProductName().isBlank() || product.getProductName() == null) {
            throw new NotValidException("The format is not valid");
        }
        productDao.updateProduct(id,product);

    }
}