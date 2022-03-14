package com.zs.assignment13.dao;

import com.zs.assignment13.ConnectionService;
import com.zs.assignment13.entity.Category;
import com.zs.assignment13.exceptions.InternalServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CategoryDaoTest {
    ConnectionService connectionService;
    Connection mockConnection;
    CategoryDao categoryDao;
    ResultSet resultSet;

    @BeforeEach
    void setup() {
        connectionService = mock(ConnectionService.class);
        categoryDao = new CategoryDao(connectionService);
        resultSet = mock(ResultSet.class);
        mockConnection = mock(Connection.class);
    }

    @Test
    void getAllCategories() throws InternalServerException, SQLException {
        Statement statement = mock(Statement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(1, categoryDao.getAllCategories().get(0).getId());
    }
    @Test
    void testGetAllCategoriesException() throws InternalServerException, SQLException {
        Statement statement = mock(Statement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).createStatement();
        assertThrows(InternalServerException.class, () -> categoryDao.getAllCategories());
    }

    @Test
    void addCategory() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        Category category= new Category(1,"electronics");
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> categoryDao.addCategory(category));
    }

    @Test
    void deleteCategory() throws SQLException, InternalServerException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> categoryDao.deleteCategory(1));
    }

    @Test
    void updateCategory() throws SQLException, InternalServerException {
        PreparedStatement statement = mock(PreparedStatement.class);
        Category category= new Category(1,"electronics");
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> categoryDao.updateCategory(category.getId(),category));
    }

    @Test
    void isExist() throws SQLException, InternalServerException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(true, categoryDao.isExist(1));

    }
    @Test
    void testIsExistException() throws SQLException, InternalServerException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> categoryDao.isExist(1));

    }

}