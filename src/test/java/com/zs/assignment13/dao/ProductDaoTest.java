package com.zs.assignment13.dao;

import com.zs.assignment13.ConnectionService;
import com.zs.assignment13.entity.Product;
import com.zs.assignment13.exceptions.InternalServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductDaoTest {
    ConnectionService connectionService;
    Connection mockConnection;
    ProductDao productDao;
    ResultSet resultSet;

    @BeforeEach
    void setup() {
        connectionService = mock(ConnectionService.class);
        productDao = new ProductDao(connectionService);
        resultSet = mock(ResultSet.class);
        mockConnection = mock(Connection.class);
    }

    @Test
    void getAllProducts() throws InternalServerException, SQLException {
        Statement statement = mock(Statement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(1, productDao.getAllProducts().get(0).getId());
    }

    @Test
    void testGetAllProductsException() throws InternalServerException, SQLException {
        Statement statement = mock(Statement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).createStatement();
        assertThrows(InternalServerException.class, () -> productDao.getAllProducts());
    }

    @Test
    void addProduct() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        Product product = new Product(1, "dell", 20, 1);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> productDao.addProduct(product));

    }

    @Test
    void updateProduct() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        Product product = new Product(1, "dell", 20, 1);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> productDao.updateProduct(product.getId(), product));
    }

    @Test
    void getAllProductsWithCategory() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(1)).thenReturn("Dell");
        assertEquals("Dell", productDao.getAllProductsWithCategory("electronics").get(0));

    }
    @Test
    void testGetAllProductsWithCategoryException() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> productDao.getAllProductsWithCategory("electronics"));

    }

    @Test
    void isExist() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(true, productDao.isExist(1));

    }
    @Test
    void testIsExistException() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> productDao.isExist(1));


    }

    @Test
    void deleteProduct() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> productDao.deleteProduct(1));

    }
}