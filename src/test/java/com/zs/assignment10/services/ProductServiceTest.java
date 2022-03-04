/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10.services;

import com.zs.assignment10.dao.ProductDao;
import com.zs.assignment10.entity.Product;
import com.zs.assignment10.exceptions.ThisIsMyException;
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
     * @throws ThisIsMyException
     */
    @Test
    void findByProductCode() throws ThisIsMyException {

        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.getByID(1)).thenReturn(product);
        assertEquals(product.getProductName(), productService.findByProductCode(1).getProductName());

    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws ThisIsMyException
     */
    @Test
    void findByProductCodeException() throws ThisIsMyException {

        Product product = new Product(1, "ish", 20F, 100);
        doThrow(ThisIsMyException.class).when(productDao).getByID(product.getProductCode());
        assertThrows(ThisIsMyException.class, () -> productService.findByProductCode(product.getProductCode()));

    }

    /**
     * Tests that the same product is not returned by fetching using product code.
     *
     * @throws ThisIsMyException
     */
    @Test
    void notFindByProductCode() throws ThisIsMyException {

        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.getByID(1)).thenReturn(product);
        assertNotEquals("isht", productService.findByProductCode(1).getProductName());

    }

    /**
     * Tests whether the product dao save method is called 1 time.
     * @throws ThisIsMyException
     */
    @Test
    void insert() throws ThisIsMyException {

        Product product = new Product(1, "ish", 20F, 100);
        productService.insert(product);
        verify(productDao, times(1)).save(product);
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws ThisIsMyException
     */
    @Test
    void insertException() throws ThisIsMyException {

        Product product = new Product(1, "ish", 20F, 100);
        doThrow(ThisIsMyException.class).when(productDao).save(product);
        assertThrows(ThisIsMyException.class, () -> productService.insert(product));
    }

    /**
     * Tests whether the product is updated using the product code or not.
     *
     * @throws ThisIsMyException
     */
    @Test
    void updateByProductCodeException() throws ThisIsMyException {
        Product product = new Product(1, "ish", 20F, 100);
        doThrow(ThisIsMyException.class).when(productDao).updateByID(product.getProductCode(), product);
        assertThrows(ThisIsMyException.class, () -> productService.updateByProductCode(product.getProductCode(), product));
    }

    /**
     * Tests that the product is not updated using the product code.
     *
     * @throws ThisIsMyException
     */
    @Test
    void UpdateByProductCode() throws ThisIsMyException {
        Product product = new Product(1, "ish", 20F, 100);
        productService.updateByProductCode(product.getProductCode(), product);
        verify(productDao, times(1)).updateByID(product.getProductCode(), product);
    }

    /**
     * Tests that the delete method is called 1 time.
     * @throws ThisIsMyException
     */
    @Test
    void deleteByProductCode() throws ThisIsMyException {
        Product product = new Product(1, "ish", 20F, 100);
        productService.deleteByProductCode(product.getProductCode());
        verify(productDao, times(1)).deleteById(product.getProductCode());
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws ThisIsMyException
     */
    @Test
    void deleteByProductCodeException() throws ThisIsMyException {
        Product product = new Product(1, "ish", 20F, 100);
        doThrow(ThisIsMyException.class).when(productDao).deleteById(product.getProductCode());
        assertThrows(ThisIsMyException.class, () -> productService.deleteByProductCode(product.getProductCode()));
    }

    /**
     * Tests that the same product list is returned.
     *
     * @throws ThisIsMyException
     */
    @Test
    void getAllProduct() throws ThisIsMyException {
        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        assertEquals(1, productService.getAllProduct().get(0).getProductCode());
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws ThisIsMyException
     */
    @Test
    void getAllProductException() throws ThisIsMyException {
        doThrow(ThisIsMyException.class).when(productDao).findAll();
        assertThrows(ThisIsMyException.class, () -> productService.getAllProduct());
    }

    /**
     * Tests that the same product list is not returned.
     *
     * @throws ThisIsMyException
     */
    @Test
    void notGetAllProduct() throws ThisIsMyException {
        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        assertNotEquals(2, productService.getAllProduct().get(0).getProductCode());
    }

    /**
     * Tests that the respective product exists.
     *
     * @throws ThisIsMyException
     */
    @Test
    void productIsExist() throws ThisIsMyException {
        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.exist(product.getProductCode())).thenReturn(true);
        assertEquals(true, productService.productIsExist(product.getProductCode()));
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws ThisIsMyException
     */
    @Test
    void productIsExistException() throws ThisIsMyException {
        Product product = new Product(1, "ish", 20F, 100);
        doThrow(ThisIsMyException.class).when(productDao).exist(product.getProductCode());
        assertThrows(ThisIsMyException.class, () -> productService.productIsExist(product.getProductCode()));
    }

    /**
     * Tests that the respective product does not exist.
     *
     * @throws ThisIsMyException
     */
    @Test
    void notProductIsExist() throws ThisIsMyException {
        Product product = new Product(1, "ish", 20F, 100);
        when(productDao.exist(product.getProductCode())).thenReturn(true);
        assertNotEquals(false, productService.productIsExist(product.getProductCode()));
    }
}