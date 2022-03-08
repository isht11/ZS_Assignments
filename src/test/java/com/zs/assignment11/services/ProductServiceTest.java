package com.zs.assignment11.services;

import com.zs.assignment11.dao.ProductDao;
import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.exceptions.InternalServerError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void saveProduct() throws InternalServerError {
        Product product = new Product(1, "Dell", 20F);
        Category category = new Category(1, "electronics", 1);
        productService.saveProduct(product,category);
        verify(productDao, times(1)).save(product,category);
    }

    @Test
    void getAllProducts() throws InternalServerError {
        Product product = new Product(1, "dell", 20F);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        assertEquals(1, productService.getAllProducts().get(0).getId());

    }

    @Test
    void getAllProductByCategory() throws InternalServerError {
        when(productDao.findAllInCategory("electronics")).thenReturn(Arrays.asList("dell"));
        assertEquals("dell", productService.getAllProductByCategory("electronics").get(0));
    }

    @Test
    void updateProduct() throws InternalServerError {
        Product product = new Product(1, "Dell", 20F);
        productService.updateProduct(product, product.getId());
        verify(productDao, times(1)).updateProduct(product.getId(),product);
    }

}