package com.zs.assignment13;

import com.zs.assignment13.exceptions.InternalServerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
    final String URL = "jdbc:postgresql://ec2-54-193-197-187.us-west-1.compute.amazonaws.com:5432/postgres";
    final String USERNAME = "postgres";
    final String PASSWORD = "root123";

    public Connection getConnection() throws InternalServerException {
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new InternalServerException("Class not found error or sql error");
        }
        return conn;
    }
}
