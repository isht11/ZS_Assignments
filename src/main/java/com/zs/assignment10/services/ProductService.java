/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10.services;

import com.zs.assignment10.dao.ProductDao;
import com.zs.assignment10.entity.Product;
import com.zs.assignment10.exceptions.ThisIsMyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Connects the controller to the product dao.
 */
public class ProductService {
    private ProductDao productDao;
    static Logger logger = LogManager.getLogger(ProductService.class.getName());

    public ProductService() {
        productDao = new ProductDao();
    }

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * Calls the product dao to save the product passed.
     * @param product
     * @throws ThisIsMyException
     */
    public void insert(Product product) throws ThisIsMyException {
            productDao.save(product);
    }

    /**
     * Returns the product by calling the product dao and searching using the product code.
     * @param productCode
     * @return
     * @throws ThisIsMyException
     */
    public Product findByProductCode(Integer productCode) throws ThisIsMyException {
            Product product;
            product = productDao.getByID(productCode);
            return product;
    }

    /**
     * Updates the product details using the product code passed.
     * @param productCode
     * @param product
     * @return
     * @throws ThisIsMyException
     */
    public void updateByProductCode(Integer productCode, Product product) throws ThisIsMyException {
            productDao.updateByID(productCode, product);
    }

    /**
     * Deletes the product using the product code passed.
     * @param productCode
     */
    public void deleteByProductCode(Integer productCode) throws ThisIsMyException {
            productDao.deleteById(productCode);
    }

    /**
     * Returns a list of all the products by calling the product dao find all method.
     * @return
     * @throws ThisIsMyException
     */
    public List<Product> getAllProduct() throws ThisIsMyException {
            return productDao.findAll();
    }

    /**
     * Checks whether the product exists or not by calling the exist method of product dao.
     * @param productCode
     * @return
     * @throws ThisIsMyException
     */
    public boolean productIsExist(int productCode) throws ThisIsMyException {
            return productDao.exist(productCode);
    }
}