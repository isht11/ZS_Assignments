package com.zs.assignment13.service;

import com.zs.assignment13.dao.ProductDao;
import com.zs.assignment13.entity.Product;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    public ProductService productService;
    private ProductDao productDao;

    @BeforeEach
    void setup() {

        productDao = mock(ProductDao.class);
        productService = new ProductService(productDao);
    }

    @Test
    void getAllProduct() throws InternalServerException {
        productService.getAllProduct();
        verify(productDao, times(1)).getAllProducts();
    }

    @Test
    void addProduct() throws InternalServerException, NotFoundError, NotValidException {
        int categoryId=1;
        Product product=new Product(1,"Laptop",15,categoryId);
        when(productDao.isExist(product.getId())).thenReturn(false);
        productService.addProduct(product);
        verify(productDao, times(1)).addProduct(product);
    }

    @Test
    void updateProduct() throws InternalServerException, NotFoundError, NotValidException {
        int categoryId=1;
        Product product=new Product(1,"Laptop",15,categoryId);
        when(productDao.isExist(product.getId())).thenReturn(true);
        productService.updateProduct(product.getId(),product);
        verify(productDao, times(1)).updateProduct(product.getId(),product);
    }

    @Test
    void getAllProductWithCategory() throws InternalServerException, NotValidException {
        String category="electronics";
        productService.getAllProductWithCategory(category);
        verify(productDao, times(1)).getAllProductsWithCategory(category);
    }

    @Test
    void deleteProduct() throws InternalServerException, NotFoundError, NotValidException {
        int productId=1;
        when(productDao.isExist(productId)).thenReturn(true);
        productService.deleteProduct(productId);
        verify(productDao, times(1)).deleteProduct(productId);
    }
    @Test
    void testDeleteProductException() throws InternalServerException{
        int productId=1;
        when(productDao.isExist(productId)).thenReturn(true);
        doThrow(InternalServerException.class).when(productDao).deleteProduct(productId);
        assertThrows(InternalServerException.class, () -> productService.deleteProduct(productId));
    }
    @Test
    void testAddProductException() throws InternalServerException{
        int categoryId=1;
        Product product=new Product(1,"Laptop",15,categoryId);
        when(productDao.isExist(product.getId())).thenReturn(false);
        doThrow(InternalServerException.class).when(productDao).addProduct(product);
        assertThrows(InternalServerException.class, () -> productService.addProduct(product));
    }
    @Test
    void testGetAllProductsWithCategoryException() throws InternalServerException{
        String category="electronics";
        doThrow(InternalServerException.class).when(productDao).getAllProductsWithCategory(category);
        assertThrows(InternalServerException.class, () -> productService.getAllProductWithCategory(category));
    }
    @Test
    void testUpdateProductsException() throws InternalServerException{
        int categoryId=1;
        Product product=new Product(1,"Laptop",15,categoryId);
        when(productDao.isExist(product.getId())).thenReturn(true);
        doThrow(InternalServerException.class).when(productDao).updateProduct(product.getId(),product);
        assertThrows(InternalServerException.class, () -> productService.updateProduct(product.getId(),product));
    }
    @Test
    void testGetAllProductsException() throws InternalServerException{
        doThrow(InternalServerException.class).when(productDao).getAllProducts();
        assertThrows(InternalServerException.class, () -> productService.getAllProduct());

    }
}