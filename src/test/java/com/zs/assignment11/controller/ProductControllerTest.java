package com.zs.assignment11.controller;

import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    private ProductController productController;
    private ProductService productService;

    @BeforeEach
    void setup() {
        productService= mock(ProductService.class);
        productController = new ProductController(productService);
    }
    @Test
    void saveProduct() {
        Product product = new Product(1, "Dell", 20F);
        Category category = new Category(1, "electronics", 1);
        productController.saveProduct(product.getId(),product.getProductName(),product.getPrice(), category.getCategoryId(), category.getCategoryName());
        verify(productService, times(1)).saveProduct(product,category);
    }

    @Test
    void getAllProducts() {
        Product product = new Product(1, "Dell", 20F);
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product));
        assertEquals(product.getId(), productController.getAllProducts().get(0).getId());
    }

    @Test
    void getProductsByCategory() {
        Product product = new Product(1, "Dell", 20F);
        when(productService.getAllProductByCategory("electronics")).thenReturn(Arrays.asList(product.getProductName()));
        assertEquals(product.getId(), productController.getAllProducts().get(0).getId());
    }

    @Test
    void update() {
        Product product = new Product(1, "Dell", 20F);
        productController.update(product.getId(),product.getProductName(),product.getPrice());
        verify(productService, times(1)).updateProduct(product, product.getId());

    }
}