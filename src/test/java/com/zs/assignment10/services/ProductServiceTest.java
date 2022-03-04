/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10.services;

import com.zs.assignment10.dao.ProductDao;
import com.zs.assignment10.entity.Product;
import com.zs.assignment10.exceptions.InternalServerError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Tests the product service class methods.
 */
class ProductServiceTest {

    private ProductService productService;
    private ProductDao productDao;

    @BeforeEach
    void setup() {

        productDao = mock(ProductDao.class);
        productService = new ProductService(productDao);
    }

    /**
     * Tests whether the same product is returned by finding by code or not.
     *
     * @throws InternalServerError
     */
    @Test
    void findByProductCode() throws InternalServerError {

        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.getByID(1)).thenReturn(product);
        assertEquals(product.getProductName(), productService.findByProductCode(1).getProductName());

    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws InternalServerError
     */
    @Test
    void findByProductCodeException() throws InternalServerError {

        Product product = new Product(1, "ish", 20F, 100);
        doThrow(InternalServerError.class).when(productDao).getByID(product.getProductCode());
        assertThrows(InternalServerError.class, () -> productService.findByProductCode(product.getProductCode()));

    }

    /**
     * Tests that the same product is not returned by fetching using product code.
     *
     * @throws InternalServerError
     */
    @Test
    void notFindByProductCode() throws InternalServerError {

        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.getByID(1)).thenReturn(product);
        assertNotEquals("isht", productService.findByProductCode(1).getProductName());

    }

    /**
     * Tests whether the product dao save method is called 1 time.
     * @throws InternalServerError
     */
    @Test
    void insert() throws InternalServerError {

        Product product = new Product(1, "ish", 20F, 100);
        productService.insert(product);
        verify(productDao, times(1)).save(product);
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws InternalServerError
     */
    @Test
    void insertException() throws InternalServerError {

        Product product = new Product(1, "ish", 20F, 100);
        doThrow(InternalServerError.class).when(productDao).save(product);
        assertThrows(InternalServerError.class, () -> productService.insert(product));
    }

    /**
     * Tests whether the product is updated using the product code or not.
     *
     * @throws InternalServerError
     */
    @Test
    void updateByProductCodeException() throws InternalServerError {
        Product product = new Product(1, "ish", 20F, 100);
        doThrow(InternalServerError.class).when(productDao).updateByID(product.getProductCode(), product);
        assertThrows(InternalServerError.class, () -> productService.updateByProductCode(product.getProductCode(), product));
    }

    /**
     * Tests that the product is not updated using the product code.
     *
     * @throws InternalServerError
     */
    @Test
    void UpdateByProductCode() throws InternalServerError {
        Product product = new Product(1, "ish", 20F, 100);
        productService.updateByProductCode(product.getProductCode(), product);
        verify(productDao, times(1)).updateByID(product.getProductCode(), product);
    }

    /**
     * Tests that the delete method is called 1 time.
     * @throws InternalServerError
     */
    @Test
    void deleteByProductCode() throws InternalServerError {
        Product product = new Product(1, "ish", 20F, 100);
        productService.deleteByProductCode(product.getProductCode());
        verify(productDao, times(1)).deleteById(product.getProductCode());
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws InternalServerError
     */
    @Test
    void deleteByProductCodeException() throws InternalServerError {
        Product product = new Product(1, "ish", 20F, 100);
        doThrow(InternalServerError.class).when(productDao).deleteById(product.getProductCode());
        assertThrows(InternalServerError.class, () -> productService.deleteByProductCode(product.getProductCode()));
    }

    /**
     * Tests that the same product list is returned.
     *
     * @throws InternalServerError
     */
    @Test
    void getAllProduct() throws InternalServerError {
        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        assertEquals(1, productService.getAllProduct().get(0).getProductCode());
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws InternalServerError
     */
    @Test
    void getAllProductException() throws InternalServerError {
        doThrow(InternalServerError.class).when(productDao).findAll();
        assertThrows(InternalServerError.class, () -> productService.getAllProduct());
    }

    /**
     * Tests that the same product list is not returned.
     *
     * @throws InternalServerError
     */
    @Test
    void notGetAllProduct() throws InternalServerError {
        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        assertNotEquals(2, productService.getAllProduct().get(0).getProductCode());
    }

    /**
     * Tests that the respective product exists.
     *
     * @throws InternalServerError
     */
    @Test
    void productIsExist() throws InternalServerError {
        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.exist(product.getProductCode())).thenReturn(true);
        assertEquals(true, productService.productIsExist(product.getProductCode()));
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws InternalServerError
     */
    @Test
    void productIsExistException() throws InternalServerError {
        Product product = new Product(1, "ish", 20F, 100);
        doThrow(InternalServerError.class).when(productDao).exist(product.getProductCode());
        assertThrows(InternalServerError.class, () -> productService.productIsExist(product.getProductCode()));
    }

    /**
     * Tests that the respective product does not exist.
     *
     * @throws InternalServerError
     */
    @Test
    void notProductIsExist() throws InternalServerError {
        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.exist(product.getProductCode())).thenReturn(true);
        assertNotEquals(false, productService.productIsExist(product.getProductCode()));
    }
}