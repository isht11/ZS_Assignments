package com.zs.assignment13.dao;

import com.zs.assignment13.ConnectionService;
import com.zs.assignment13.entity.User;
import com.zs.assignment13.exceptions.InternalServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserDaoTest {
    ConnectionService connectionService;
    Connection mockConnection;
    UserDao userDao;
    ResultSet resultSet;

    @BeforeEach
    void setup() {
        connectionService = mock(ConnectionService.class);
        userDao = new UserDao(connectionService);
        resultSet = mock(ResultSet.class);
        mockConnection = mock(Connection.class);
    }

    @Test
    void getAllUsers() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(1, userDao.getAllUsers().get(0).getId());
    }
    @Test
    void testGetAllUsersException() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).createStatement();
        assertThrows(InternalServerException.class, () -> userDao.getAllUsers());
    }

    @Test
    void addUser() throws InternalServerException, SQLException {
        User user=new User(1,"Ishtmeet","9560002322");
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> userDao.addUser(user));

    }

    @Test
    void isExist() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(true, userDao.isExist(1));
    }
    @Test
    void testIsExistException() throws InternalServerException, SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connectionService.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(SQLException.class).when(mockConnection).prepareStatement(anyString());
        assertThrows(InternalServerException.class, () -> userDao.isExist(1));
    }
}