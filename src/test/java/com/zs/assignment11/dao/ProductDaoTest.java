package com.zs.assignment11.dao;

import com.zs.assignment11.dbConnection.DBConnection;
import com.zs.assignment11.exceptions.InternalServerError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductDaoTest {
    ProductDao productDao;
    DBConnection dBConnection;

    @BeforeEach
    public void setUp() {
        dBConnection = mock(DBConnection.class);
        productDao = new ProductDao();
    }

    @Test
    void save() throws SQLException, InternalServerError {
        Statement statement=mock(Statement.class);
        Connection mockConnection=mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeUpdate(anyString())).thenReturn(1);
        assertEquals(1, statement.executeUpdate(anyString()));
    }

    @Test
    void updateProduct() throws SQLException, InternalServerError {
        Statement statement=mock(Statement.class);
        Connection mockConnection=mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeUpdate(anyString())).thenReturn(1);
        assertEquals(1, statement.executeUpdate(anyString()));
    }

    @Test
    void findAll() throws InternalServerError, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        Connection mockConnection=mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        ResultSet set = mock(ResultSet.class);
        when(statement.executeQuery(anyString())).thenReturn(set);
        when(set.next()).thenReturn(true);
        when(set.getInt(1)).thenReturn(1);
        assertEquals(1, set.getInt(1));
    }

    @Test
    void findAllInCategory() throws SQLException, InternalServerError {
        PreparedStatement statement = mock(PreparedStatement.class);
        Connection mockConnection=mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        ResultSet set = mock(ResultSet.class);
        when(statement.executeQuery(anyString())).thenReturn(set);
        when(set.next()).thenReturn(true);
        when(set.getInt(1)).thenReturn(1);
        assertEquals(1, set.getInt(1));

    }

    @Test
    void testSaveException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        Assertions.assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        Assertions.assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        Assertions.assertThrows(SQLException.class ,()->conn.createStatement());
    }

    @Test
    void testUpdateProductException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        Assertions.assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        Assertions.assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        Assertions.assertThrows(SQLException.class ,()->conn.createStatement());
    }
    @Test
    void testFindAllException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        Assertions.assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        Assertions.assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        Assertions.assertThrows(SQLException.class ,()->conn.createStatement());
    }
    @Test
    void testFindAllInCategoryException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        Assertions.assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        Assertions.assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        Assertions.assertThrows(SQLException.class ,()->conn.createStatement());
    }

}