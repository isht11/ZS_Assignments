/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.dao;

import com.zs.assignment11.controller.ProductController;
import com.zs.assignment11.exceptions.InternalServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * connects to the database and has methods to insert, update, delete to the table.
 */
public class CategoryDao {

    static final String URL = "jdbc:postgresql://localhost:2006/student";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "root123";
    Logger logger = LogManager.getLogger(ProductController.class.getName());

    /**
     * Connects to the database.
     *
     * @return
     */
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

    /**
     * Gets all the categories present in the table.
     * @return
     * @throws InternalServerError
     */
    public List<String> getAll() throws InternalServerError {

        ArrayList<String> categoryList = new ArrayList<>();
        final String QUERY = "select DISTINCT category_name from Category";
        Statement statement;
        try {
            Connection con = this.connectionToDatabase();
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {

                categoryList.add(resultSet.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("There is an SQL exception");
        }
        return categoryList;

    }




}
