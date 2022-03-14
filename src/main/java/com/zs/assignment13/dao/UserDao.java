package com.zs.assignment13.dao;

import com.zs.assignment13.ConnectionService;
import com.zs.assignment13.entity.User;
import com.zs.assignment13.exceptions.InternalServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    ConnectionService connectionService;
    final String getQuery = "select * from users";
    final String addQuery = "insert into users values(?,?,?)";
    final String existQuery = "SELECT COUNT(*) FROM users WHERE user_id = ?";

    public UserDao() {
        connectionService = new ConnectionService();
    }

    public UserDao(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public List<User> getAllUsers() throws InternalServerException {
        Connection con = connectionService.getConnection();
        List<User> userList;
        try {
            Statement statement = con.createStatement();
            ResultSet set = statement.executeQuery(getQuery);
            userList = new ArrayList<>();
            while (set.next()) {
                User user = new User(set.getInt(1), set.getString(2), set.getString(3));
                userList.add(user);
            }
            con.close();
        } catch (SQLException e) {
            throw new InternalServerException("There was an SQL exception");
        }
        return userList;
    }

    public void addUser(User user) throws InternalServerException {
        Connection con = connectionService.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(addQuery);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getMobileNumber());
            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new InternalServerException("There was an SQL exception");
        }
    }

    public boolean isExist(int userId) throws InternalServerException {
        ResultSet resultSet;
        try {
            Connection con = connectionService.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(existQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) > 0) {
                    return true;
                }
            }
            con.close();
        } catch (SQLException | InternalServerException e) {
            throw new InternalServerException("Sql error occurred");
        }
        return false;
    }
}
