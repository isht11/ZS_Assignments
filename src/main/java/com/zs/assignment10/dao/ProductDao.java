/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10.dao;

import com.zs.assignment10.controller.ProductController;
import com.zs.assignment10.entity.Product;
import com.zs.assignment10.exceptions.InternalServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * connects to the database and has methods to insert, update, delete to the table.
 */
public class ProductDao {

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

    public void save(Product product) throws InternalServerError {

        Connection con = this.connectionToDatabase();
        Statement statement;
        final String query = "INSERT INTO product VALUES(" + product.getProductCode() + ", '" + product.getProductName() + "'," + product.getPrice() + ", " + product.getQuantity() + ");";
        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("SQL exception occurred");
        }

    }

    public Product getByID(Integer productCode) throws InternalServerError {

        final String query = "select * from product where productCode = ?";
        Product product = new Product();
        try {
            Connection con = this.connectionToDatabase();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            con.close();
            if (resultSet.next()) {
                product.setProductCode(resultSet.getInt(1));
                product.setProductName(resultSet.getString(2));
                product.setPrice(resultSet.getFloat(3));
                product.setQuantity(resultSet.getInt(4));

            } else {
                logger.info("Product Does not exist");
            }
        } catch (SQLException e) {
            throw new InternalServerError("There is a sql exception");
        }
        return product;
    }

    public void updateByID(Integer productCode, Product product) throws InternalServerError {
        final String QUERY = "update product set productName=?, price=?, quantity=? where productCode=?";
        try {
            Connection con = this.connectionToDatabase();
            PreparedStatement preparedStatement = con.prepareStatement(QUERY);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, productCode);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("Sql exception occurred");
        }

    }

    public void deleteById(Integer productCode) throws InternalServerError {
        final String QUERY = "DELETE FROM product where productCode =" + productCode;
        Statement statement;
        try {
            Connection con = this.connectionToDatabase();
            statement = con.createStatement();
            statement.executeUpdate(QUERY);
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("There is a SQL error");
        }
    }

    public List<Product> findAll() throws InternalServerError {
        ArrayList<Product> productList = new ArrayList<>();
        final String QUERY = "select * from product";
        Statement statement;
        try {
            Connection con = this.connectionToDatabase();
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductCode(resultSet.getInt(1));
                product.setProductName(resultSet.getString(2));
                product.setPrice(resultSet.getFloat(3));
                product.setQuantity(resultSet.getInt(4));
                productList.add(product);
            }
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("There is an SQL exception");
        }
        return productList;

    }

    public boolean exist(int productCode) throws InternalServerError {
        ResultSet resultSet;
        final String QUERY = "SELECT COUNT(*) FROM product WHERE productCode = ?";
        try {
            Connection con = this.connectionToDatabase();
            PreparedStatement preparedStatement = con.prepareStatement(QUERY);
            preparedStatement.setInt(1, productCode);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) > 0) {
                    return true;
                }
            }
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("Sql error occurred");
        }
        return false;
    }
}