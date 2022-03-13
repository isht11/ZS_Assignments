package com.zs.assignment11.services;

import com.zs.assignment11.dao.ProductDao;
import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.exceptions.InternalServerError;
import com.zs.assignment11.exceptions.NotValidException;
import com.zs.assignment11.exceptions.ProductNotFoundError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    private ProductService productService;
    private ProductDao productDao;

    @BeforeEach
    void setup() {

        productDao = mock(ProductDao.class);
        productService = new ProductService(productDao);
    }

    @Test
    void saveProduct() throws InternalServerError, ProductNotFoundError, NotValidException {
        Product product = new Product(1, "Dell", 20F);
        Category category = new Category(1, "electronics", 1);
        productService.saveProduct(product, category);
        verify(productDao, times(1)).save(product, category);
    }

    @Test
    void getAllProducts() throws InternalServerError {
        Product product = new Product(1, "dell", 20F);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        assertEquals(1, productService.getAllProducts().get(0).getId());

    }

    @Test
    void getAllProductByCategory() throws InternalServerError, NotValidException {
        when(productDao.findAllInCategory("electronics")).thenReturn(Arrays.asList("dell"));
        assertEquals("dell", productService.getAllProductByCategory("electronics").get(0));
    }

    @Test
    void updateProduct() throws InternalServerError, ProductNotFoundError, NotValidException {
        Product product = new Product(1, "Dell", 20F);
        productService.updateProduct(product, product.getId());
        verify(productDao, times(1)).updateProduct(product.getId(), product);
    }

    @Test
    void testUpdateProductException() throws InternalServerError {
        Product product = new Product(1, "Dell", 20F);
        doThrow(InternalServerError.class).when(productDao).updateProduct(product.getId(), product);
        assertThrows(ProductNotFoundError.class, () -> productService.updateProduct(product, product.getId()));
    }

    @Test
    void testGetAllProductByCategoryException() throws InternalServerError {
        doThrow(InternalServerError.class).when(productDao).findAllInCategory("Electronics");
        assertThrows(InternalServerError.class, () -> productService.getAllProductByCategory("Electronics"));
    }

    @Test
    void testGetAllProductsException() throws InternalServerError {
        doThrow(InternalServerError.class).when(productDao).findAll();
        assertThrows(InternalServerError.class, () -> productService.getAllProducts());
    }

    @Test
    void testSaveProductException() throws InternalServerError, ProductNotFoundError {
        Product product = new Product(1, "Dell", 20F);
        Category category = new Category(1, "electronics", 1);
        doThrow(InternalServerError.class).when(productDao).save(product, category);
        assertThrows(InternalServerError.class, () -> productService.saveProduct(product, category));

    }


}