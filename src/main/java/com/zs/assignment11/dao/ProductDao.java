/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.dao;

import com.zs.assignment11.dbConnection.DBConnection;
import com.zs.assignment11.entity.Category;
import com.zs.assignment11.entity.Product;
import com.zs.assignment11.exceptions.InternalServerError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * connects to the database and has methods to insert, update, delete to the table.
 */
public class ProductDao {
    DBConnection dbConnection;
    public ProductDao(){
        dbConnection = new DBConnection();
    }

    public ProductDao(DBConnection dbConnection){
        this.dbConnection = dbConnection;
    }
    /**
     * Saves the product to the table.
     * @param product
     * @param category
     * @throws InternalServerError
     */
    public void save(Product product, Category category) throws InternalServerError {

        Connection con = dbConnection.connectionToDatabase();
        Statement statement;
        String QUERY1= "INSERT INTO Product VALUES(" + product.getId() + ", '" + product.getProductName() + "'," + product.getPrice() + ");";
        String QUERY2 = "INSERT INTO Category VALUES(" + category.getCategoryId() + ", '" + category.getCategoryName() + "'," + product.getId() + ");";
        try {
            statement = con.createStatement();
            statement.executeUpdate(QUERY1);
            statement.executeUpdate(QUERY2);
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("SQL exception occurred");
        }

    }

    /**
     * Updates the product details in the table.
     * @param productId
     * @param product
     * @throws InternalServerError
     */
    public void updateProduct(Integer productId, Product product) throws InternalServerError {
        String QUERY= "update product set product_name = ?, price = ? where product_id=?";
        try {
            Connection con = dbConnection.connectionToDatabase();
            PreparedStatement preparedStatement = con.prepareStatement(QUERY);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setInt(3, productId);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("Sql exception occurred");
        }

    }

    /**
     * returns all the products in the table.
     * @return
     * @throws InternalServerError
     */
    public List<Product> findAll() throws InternalServerError {
        ArrayList<Product> productList = new ArrayList<>();
        String QUERY = "select * from product";
        Statement statement;
        try {
            Connection con = dbConnection.connectionToDatabase();
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setProductName(resultSet.getString(2));
                product.setPrice(resultSet.getFloat(3));
                productList.add(product);
            }
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("There is an SQL exception");
        }
        return productList;

    }

    /**
     * Returns all the products present in the user defined category.
     * @param parent
     * @return
     * @throws InternalServerError
     */
    public List<String> findAllInCategory(String parent) throws InternalServerError {
        ArrayList<String> productList = new ArrayList<>();
        String QUERY = "select product.product_name from product INNER JOIN category ON category.product_id = product.product_id where category_name = ?";
        PreparedStatement preparedStatement;
        try {
            Connection con = dbConnection.connectionToDatabase();
            preparedStatement= con.prepareStatement(QUERY);
            preparedStatement.setString(1,parent);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(resultSet.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            throw new InternalServerError("There is an SQL exception");
        }
        return productList;

    }
    public boolean exist(int productCode) throws InternalServerError {
        ResultSet resultSet;
        String existQuery = "SELECT COUNT(*) FROM product WHERE product_id = ?";
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