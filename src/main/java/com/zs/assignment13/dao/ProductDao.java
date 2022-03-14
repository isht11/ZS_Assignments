package com.zs.assignment13.dao;

import com.zs.assignment13.ConnectionService;
import com.zs.assignment13.entity.Product;
import com.zs.assignment13.exceptions.InternalServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    ConnectionService connectionService;
    final String selectQuery = "Select * from products";
    final String addQuery = "insert into products values(?,?,?,?)";
    final String updateQuery = "update products set name = ?, price = ?,category_id = ? where product_id = ?";
    final String getAllQuery = "select products.name from products INNER JOIN category ON products.category_id = category.category_id where category.name = ?";
    final String existQuery = "SELECT COUNT(*) FROM products WHERE product_id = ?";
    final String deleteQuery = "delete from products where product_id=?";

    public ProductDao() {
        connectionService = new ConnectionService();
    }

    public ProductDao(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public List<Product> getAllProducts() throws InternalServerException {
        List<Product> result;
        try {
            Connection conn = connectionService.getConnection();
            Statement statement = conn.createStatement();
            result = new ArrayList<>();
            ResultSet rs = statement.executeQuery(selectQuery);
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4));
                result.add(product);
            }
            conn.close();
        } catch (SQLException e) {
            throw new InternalServerException("Sql error occurred");
        }
        return result;
    }

    public void addProduct(Product product) throws InternalServerException {
        Connection con = connectionService.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(addQuery);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getCategory());
            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new InternalServerException("There was an SQL exception");
        }
    }

    public void updateProduct(int id, Product product) throws InternalServerException {
        Connection con = connectionService.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getCategory());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new InternalServerException("There was an SQL exception");

        }
    }

    public List<String> getAllProductsWithCategory(String category) throws InternalServerException {
        ArrayList<String> productList = new ArrayList<>();
        Connection con = connectionService.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(getAllQuery);
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(resultSet.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            throw new InternalServerException("There is an SQL exception");
        }
        return productList;
    }

    public boolean isExist(int productId) throws InternalServerException {
        ResultSet resultSet;
        try {
            Connection con = connectionService.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(existQuery);
            preparedStatement.setInt(1, productId);
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

    public void deleteProduct(int id) throws InternalServerException {
        Connection con = connectionService.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(deleteQuery);
            statement.setInt(1, id);
            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new InternalServerException("There was an SQL exception");
        }
    }

}
