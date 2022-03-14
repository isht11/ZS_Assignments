package com.zs.assignment13.service;

import com.zs.assignment13.dao.ProductDao;
import com.zs.assignment13.entity.Product;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductDao productDao;
    private final Logger logger = LogManager.getLogger(ProductDao.class);


    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductService() {
        productDao = new ProductDao();
    }

    public List<Product> getAllProduct() throws InternalServerException {

        return this.productDao.getAllProducts();
    }

    public void addProduct(@NotNull Product product) throws InternalServerException, NotFoundError, NotValidException {
        if (productDao.isExist(product.getId())) {
            throw new NotFoundError("product already exists");
        }
        if (product.getId() < 0 || product.getName().isBlank() || product.getName() == null || product.getPrice() < 0) {
            throw new NotValidException("The format is not valid");
        }
        productDao.addProduct(product);
    }

    public void updateProduct(int id, @NotNull Product product) throws InternalServerException, NotFoundError, NotValidException {
        if (!productDao.isExist(product.getId())) {
            throw new NotFoundError("product does not exist");
        }
        if (product.getId() < 0 || product.getName().isBlank() || product.getName() == null || product.getPrice() < 0) {
            throw new NotValidException("The format is not valid");
        }
        productDao.updateProduct(id, product);
    }

    public List<String> getAllProductWithCategory(String category) throws InternalServerException, NotValidException {
        if (category.isBlank()) {
            throw new NotValidException("The format is not valid");
        }
        return this.productDao.getAllProductsWithCategory(category);
    }

    public void deleteProduct(int id) throws InternalServerException, NotFoundError, NotValidException {
        if (!productDao.isExist(id)) {
            throw new NotFoundError("product does not exist");
        }
        if (id < 0) {
            throw new NotValidException("The format is not valid");
        }
        productDao.deleteProduct(id);
    }

}
