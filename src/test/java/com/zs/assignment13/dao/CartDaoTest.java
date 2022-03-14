package com.zs.assignment13.dao;

import com.zs.assignment13.ConnectionService;
import com.zs.assignment13.exceptions.InternalServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CartDaoTest {

    ConnectionService connectionService;
    Connection mockConnection;
    CartDao cartDao;
    ResultSet resultSet;

    @BeforeEach
    void setup() {
        connectionService =mock(ConnectionService.class);
        cartDao=new CartDao(connectionService);
        resultSet = mock(ResultSet.class);
        mockConnection=mock(Connection.class);
    }
    @Test
    void getCart() throws InternalServerException, SQLException {

        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doNothing().when(statement).setInt(anyInt(),anyInt());
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(1, cartDao.getCart(1).get(0).getProduct().getId());

    }
    @Test
    void testGetCartException() throws InternalServerException, SQLException {

        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> cartDao.getCart(1));

    }

    @Test
    void removeProductFromCart() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> cartDao.removeProductFromCart(1,1));
    }

    @Test
    void addProductInCart() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> cartDao.addProductInCart(1,1));
    }
}