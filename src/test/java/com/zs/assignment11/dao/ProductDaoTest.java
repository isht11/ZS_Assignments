package com.zs.assignment11.dao;

import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.exceptions.InternalServerError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductDaoTest {
    ProductDao productDao;
    @Mock
    private Connection mockConnection;

    @BeforeEach
    void setup() {
        productDao = new ProductDao();
    }

    @Test
    void save() throws SQLException, InternalServerError {
        Statement mockStatement = mockConnection.createStatement();
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        Product product = new Product(1, "dell", 1F);
        Category category = new Category(1, "electronics", 1);
        productDao.save(product, category);
        verify(mockStatement, times(1)).executeUpdate(anyString());
    }

    @Test
    void updateProduct() throws SQLException, InternalServerError {
        PreparedStatement mockStatement = mockConnection.prepareStatement(anyString());
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        Product product = new Product(1, "dell", 1F);
        productDao.updateProduct(product.getId(), product);
        verify(mockStatement, times(1)).executeUpdate(anyString());
    }

    @Test
    void findAll() throws InternalServerError, SQLException {
        Statement statement = mock(Statement.class);
        ResultSet set = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(set);
        when(set.next()).thenReturn(true);
        when(set.getInt(1)).thenReturn(1);
        Assertions.assertEquals(1, productDao.findAll().get(0).getId());
    }

    @Test
    void findAllInCategory() throws SQLException, InternalServerError {
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet set = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(set);
        when(set.next()).thenReturn(true);
        when(set.getString(1)).thenReturn("Dell");
        Assertions.assertEquals("dell", productDao.findAllInCategory("laptop").get(0));

    }

    @Test
    void testSaveException() throws SQLException, InternalServerError {
        Statement mockStatement = mockConnection.createStatement();
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        Product product = new Product(1, "dell", 1F);
        Category category = new Category(1, "electronics", 1);
        productDao.save(product, category);
        doThrow(InternalServerError.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerError.class, () -> productDao.save(product, category));
    }

    @Test
    void testUpdateProductException() throws SQLException, InternalServerError {
        PreparedStatement mockStatement = mockConnection.prepareStatement(anyString());
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        Product product = new Product(1, "dell", 1F);
        productDao.updateProduct(product.getId(),product);
        doThrow(InternalServerError.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerError.class, () -> productDao.updateProduct(product.getId(), product));
    }
    @Test
    void testFindAllException() throws SQLException {
        Statement mockStatement = mockConnection.createStatement();
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        doThrow(InternalServerError.class).when(mockConnection).createStatement();
        assertThrows(InternalServerError.class, () -> productDao.findAll());
    }
    @Test
    void testFindAllInCategoryException() throws SQLException {
        PreparedStatement mockStatement = mockConnection.prepareStatement(anyString());
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        doThrow(InternalServerError.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerError.class, () -> productDao.findAllInCategory("electronics"));
    }

}