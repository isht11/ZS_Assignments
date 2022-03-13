package com.zs.assignment11.dao;

import com.zs.assignment11.dbConnection.DBConnection;
import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.exceptions.InternalServerError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductDaoTest {
    ProductDao productDao;
    DBConnection dBConnection;
    Connection mockConnection;
    ResultSet resultSet;

    @BeforeEach
    public void setUp() {
        dBConnection = mock(DBConnection.class);
        productDao = new ProductDao(dBConnection);
        resultSet = mock(ResultSet.class);
        mockConnection = mock(Connection.class);
    }

    @Test
    public void testSaveException() throws SQLException, InternalServerError {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mockConnection.prepareStatement(anyString());
        Product product = new Product(1, "laptop", 20F);
        Category category = new Category(1, "electronics", 1);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerError.class, () -> productDao.save(product, category));
    }

    @Test
    void testUpdateProductException() throws SQLException, InternalServerError {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mockConnection.prepareStatement(anyString());
        Product product = new Product(1, "laptop", 20F);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerError.class, () -> productDao.updateProduct(product.getId(), product));
    }

    @Test
    void findAll() throws InternalServerError, SQLException {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        Product product = new Product(1, "laptop", 20F);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(1, productDao.findAll().get(0).getId());
    }

    @Test
    void testFindAllException() throws InternalServerError, SQLException {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        Product product = new Product(1, "laptop", 20F);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        doThrow(SQLException.class).when(mockConnection).createStatement();
        assertThrows(InternalServerError.class, () -> productDao.findAll());

    }

    @Test
    void findAllInCategory() throws SQLException, InternalServerError {
        Connection mockConnection = mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString(1)).thenReturn("laptop");
        assertEquals("laptop", productDao.findAllInCategory("Electronics").get(0));

    }

    @Test
    void testFindAllInCategoryException() throws SQLException, InternalServerError {
        Connection mockConnection = mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerError.class, () -> productDao.findAllInCategory("electronics"));
    }


    @Test
    void testExistException() throws InternalServerError, SQLException {
        Connection mockConnection = mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerError.class, () -> productDao.exist(1));
    }

    @Test
    void exist() throws InternalServerError, SQLException {
        Connection mockConnection = mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(true, productDao.exist(1));

    }
}