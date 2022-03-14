package com.zs.assignment13.service;

import com.zs.assignment13.dao.UserDao;
import com.zs.assignment13.entity.User;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {
    public UserService userService;
    private UserDao userDao;

    @BeforeEach
    void setup() {

        userDao = mock(UserDao.class);
        userService = new UserService(userDao);
    }

    @Test
    void getAllUsers() throws InternalServerException {
        userService.getAllUsers();
        verify(userDao, times(1)).getAllUsers();

    }

    @Test
    void addUser() throws InternalServerException, NotFoundError, NotValidException {
        User user=new User(1,"Ishtmeet","9560002322");
        when(userDao.isExist(user.getId())).thenReturn(false);
        userService.addUser(user);
        verify(userDao, times(1)).addUser(user);
    }
    @Test
    void testAddUserException() throws InternalServerException, NotFoundError {
        User user=new User(1,"Ishtmeet","9560002322");
        when(userDao.isExist(user.getId())).thenReturn(false);
        doThrow(InternalServerException.class).when(userDao).addUser(user);
        assertThrows(InternalServerException.class, () -> userService.addUser(user));
    }
    @Test
    void testGetAllUsersException() throws InternalServerException {
        doThrow(InternalServerException.class).when(userDao).getAllUsers();
        assertThrows(InternalServerException.class, () -> userService.getAllUsers());

    }
}