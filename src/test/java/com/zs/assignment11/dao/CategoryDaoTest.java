package com.zs.assignment11.dao;

import com.zs.assignment11.dbConnection.DBConnection;
import com.zs.assignment11.exceptions.InternalServerError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CategoryDaoTest {
    CategoryDao categoryDao;
    DBConnection dBConnection;
    Connection mockConnection;
    ResultSet resultSet;

    @BeforeEach
    void setup() {
        dBConnection = mock(DBConnection.class);
        categoryDao = new CategoryDao(dBConnection);
        resultSet = mock(ResultSet.class);
        mockConnection=mock(Connection.class);
    }

    @Test
    void getAll() throws SQLException, InternalServerError {
        Statement statement = mock(Statement.class);
        ResultSet set = mock(ResultSet.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(set);
        when(set.next()).thenReturn(true).thenReturn(false);
        when(set.getString(1)).thenReturn("electronics");
        Assertions.assertEquals("electronics", categoryDao.getAll().get(0));
    }
    @Test
    void TestGetAllException() throws SQLException, InternalServerError {
        Statement mockStatement = mockConnection.createStatement();
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        doThrow(SQLException.class).when(mockConnection).createStatement();
        assertThrows(InternalServerError.class, () -> categoryDao.getAll());
    }
}