package com.zs.assignment13.service;

import com.zs.assignment13.dao.CartDao;
import com.zs.assignment13.dao.ProductDao;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    private CartService cartService;
    private CartDao cartDao;
    private ProductDao productDao;

    @BeforeEach
    void setup() {

        cartDao = mock(CartDao.class);
        productDao = mock(ProductDao.class);
        cartService = new CartService(cartDao,productDao);

    }

    @Test
    void addProductIntoCart() throws InternalServerException, NotFoundError, NotValidException {
        int userId=1;
        int productId=1;
        when(productDao.isExist(productId)).thenReturn(true);
        cartService.addProductIntoCart(userId,productId);
        verify(cartDao, times(1)).addProductInCart(productId,userId);
    }

    @Test
    void removeProductFromCart() throws InternalServerException, NotFoundError, NotValidException {
        int userId=1;
        int productId=1;
        when(productDao.isExist(productId)).thenReturn(true);
        cartService.removeProductFromCart(userId,productId);
        verify(cartDao, times(1)).removeProductFromCart(productId,userId);

    }

    @Test
    void getCart() throws InternalServerException, NotValidException {
        int userId=1;
        cartService.getCart(userId);
        verify(cartDao, times(1)).getCart(userId);
    }
    @Test
    void testGetCartException() throws InternalServerException {
        int userId=101;
        doThrow(InternalServerException.class).when(cartDao).getCart(userId);
        assertThrows(InternalServerException.class, ()-> cartService.getCart(userId));
    }
    @Test
    void testRemoveProductFromCartException() throws InternalServerException{
        int userId=1;
        int productId=1;
        when(productDao.isExist(productId)).thenReturn(true);
        doThrow(InternalServerException.class).when(cartDao).removeProductFromCart(userId,productId);
        assertThrows(InternalServerException.class, ()-> cartService.removeProductFromCart(userId,productId));
    }
    @Test
    void testAddProductInCartException() throws InternalServerException{
        int userId=1;
        int productId=1;
        when(productDao.isExist(productId)).thenReturn(true);
        doThrow(InternalServerException.class).when(cartDao).addProductInCart(userId,productId);
        assertThrows(InternalServerException.class, ()-> cartService.addProductIntoCart(userId,productId));
    }

}