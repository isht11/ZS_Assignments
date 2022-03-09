package com.zs.assignment11.dbConnection;

import com.zs.assignment11.exceptions.InternalServerError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String URL = "jdbc:postgresql://localhost:2006/student";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "root123";
    public Connection connectionToDatabase() throws InternalServerError {
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new InternalServerError("Class not found error or sql error");
        }
        return conn;
    }
}
