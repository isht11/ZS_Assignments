package com.zs.assignment13.service;

import com.zs.assignment13.dao.UserDao;
import com.zs.assignment13.entity.User;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserDao userDao;
    public UserService(UserDao userDao){

        this.userDao = userDao;
    }

    public UserService()  {
            this.userDao = new UserDao();
    }

    public List<User> getAllUsers() throws InternalServerException {
        return userDao.getAllUsers();
    }

    public void addUser(User user) throws InternalServerException, NotFoundError, NotValidException {
        if(userDao.isExist(user.getId())){
            throw new NotFoundError("User already exists");
        }
        if (user.getId() < 0 || user.getUserName().isBlank() || user.getUserName() == null || user.getMobileNumber().length()>10) {
            throw new NotValidException("The format is not valid");
        }
        userDao.addUser(user);
    }

}
