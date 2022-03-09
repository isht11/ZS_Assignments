/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10.dao;

import com.zs.assignment10.controller.ProductController;
import com.zs.assignment10.dbConnection.DBConnection;
import com.zs.assignment10.entity.Product;
import com.zs.assignment10.exceptions.InternalServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * connects to the database and has methods to insert, update, delete to the table.
 */
public class ProductDao {
    Logger logger = LogManager.getLogger(ProductController.class.getName());
    DBConnection dbConnection;
    final String insertQuery = "INSERT INTO product VALUES(?,?,?,?);";
    final String getByIdQuery = "select * from product where productCode = ?";
    final String updateQuery = "update product set productName=?, price=?, quantity=? where productCode=?";
    final String deleteQuery= "DELETE FROM product where productCode =?";
    final String findQuery = "select * from product";
    final String existQuery = "SELECT COUNT(*) FROM product WHERE productCode = ?";

    public ProductDao() {
        dbConnection = new DBConnection();
    }

    /**
     * Inserts product in the database.
     * @param product
     * @throws InternalServerError
     */
    public void save(Product product) throws InternalServerError {

        Connection con = dbConnection.connectionToDatabase();
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(insertQuery);
            statement.setInt(1, product.getProductCode());
            statement.setString(2, product.getProductName());
            statement.setFloat(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.executeUpdate(insertQuery);
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("SQL exception occurred");
        }

    }

    /**
     * Gets all product details using product id.
     * @param productCode
     * @return
     * @throws InternalServerError
     */
    public Product getByID(Integer productCode) throws InternalServerError {

        Product product = new Product();
        try {
            Connection con = dbConnection.connectionToDatabase();
            PreparedStatement preparedStatement = con.prepareStatement(getByIdQuery);
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

    /**
     * Updates the product given the product id.
     * @param productCode
     * @param product
     * @throws InternalServerError
     */
    public void updateByID(Integer productCode, Product product) throws InternalServerError {
        try {
            Connection con = dbConnection.connectionToDatabase();
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, productCode);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("Sql exception occurred");
        }

    }

    /**
     * deletes the product given the product id.
     * @param productCode
     * @throws InternalServerError
     */
    public void deleteById(Integer productCode) throws InternalServerError {
        PreparedStatement statement;
        try {
            Connection con = dbConnection.connectionToDatabase();
            statement = con.prepareStatement(deleteQuery);
            statement.setInt(1, productCode);
            statement.executeUpdate(deleteQuery);
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("There is a SQL error");
        }
    }

    /**
     * returns all the products in the database.
     * @return
     * @throws InternalServerError
     */
    public List<Product> findAll() throws InternalServerError {
        ArrayList<Product> productList = new ArrayList<>();
        Statement statement;
        try {
            Connection con = dbConnection.connectionToDatabase();
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(findQuery);
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

    /**
     * Checks if the product exists or not.
     * @param productCode
     * @return
     * @throws InternalServerError
     */
    public boolean exist(int productCode) throws InternalServerError {
        ResultSet resultSet;
        try {
            Connection con = dbConnection.connectionToDatabase();
            PreparedStatement preparedStatement = con.prepareStatement(existQuery);
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