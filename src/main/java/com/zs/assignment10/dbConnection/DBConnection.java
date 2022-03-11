package com.zs.assignment10.dbConnection;

import com.zs.assignment10.exceptions.InternalServerError;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    Properties properties;
    String url;
    String userName;
    String passWord;
    public DBConnection() throws InternalServerError {
        try {
            Class.forName("org.postgresql.Driver");
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/dbConfig.properties");
            properties = new Properties();
            properties.load(fileInputStream);
            url = properties.getProperty("url");
            userName = properties.getProperty("username");
            passWord = properties.getProperty("password");
        } catch (ClassNotFoundException | IOException e) {
            throw new InternalServerError("Class not Found or IOException");
        }
    }
    public Connection connectionToDatabase() throws InternalServerError {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException e) {
            throw new InternalServerError("Sql exception or IO exception or Class not found");
        }
        return connection;
    }
}
