package com.zs.assignment10.dbConnection;

import com.zs.assignment10.exceptions.InternalServerError;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public Connection connectionToDatabase() throws InternalServerError {
        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/dbConfig.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String url = properties.getProperty("url");
            String userName = properties.getProperty("username");
            String passWord = properties.getProperty("password");
            connection = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new InternalServerError("Sql exception or IO exception or Class not found");
        }
        return connection;
    }
}
