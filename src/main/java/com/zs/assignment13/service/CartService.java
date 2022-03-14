package com.zs.assignment13.service;

import com.zs.assignment13.dao.CartDao;
import com.zs.assignment13.dao.CategoryDao;
import com.zs.assignment13.dao.ProductDao;
import com.zs.assignment13.entity.CartProduct;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    public CartDao cartDao;
    private ProductDao productDao;

    public CartService() {
        cartDao = new CartDao();
        productDao = new ProductDao();
    }

    private final Logger logger = LogManager.getLogger(CategoryDao.class);

    public CartService(CartDao cartDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.productDao = productDao;
    }


    public void addProductIntoCart(int productId, int userId) throws NotFoundError, InternalServerException, NotValidException {
        if (!productDao.isExist(productId)) {
            throw new NotFoundError("product does not exist");
        }
        if (productId < 0 || userId < 0) {
            throw new NotValidException("The format is not valid");
        }
        cartDao.addProductInCart(productId, userId);
    }

    public void removeProductFromCart(int userId, int productId) throws InternalServerException, NotFoundError, NotValidException {
        if (!productDao.isExist(productId)) {
            throw new NotFoundError("product does not exist");
        }
        if (productId < 0 || userId < 0) {
            throw new NotValidException("The format is not valid");
        }
        cartDao.removeProductFromCart(userId, productId);
    }

    public List<CartProduct> getCart(int userId) throws InternalServerException, NotValidException {
        if (userId < 0) {
            throw new NotValidException("The format is not valid");
        }
        return cartDao.getCart(userId);
    }


}
