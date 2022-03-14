package com.zs.assignment13.dao;

import com.zs.assignment13.ConnectionService;
import com.zs.assignment13.entity.CartProduct;
import com.zs.assignment13.entity.TempProduct;
import com.zs.assignment13.exceptions.InternalServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    ConnectionService connectionService;
    final String getQuery = "select products.product_id, products.name, products.price, category.name, cart.quantity from products JOIN cart ON products.product_id =cart.product_id " + "JOIN category ON category.category_id=products.category_id where user_id = ? ";
    final String selectQuery = "select * from cart where user_id = ? and product_id = ?;";
    final String removeQuery = "delete from cart where user_id = ? and product_id = ? ";
    final String updateQuery = "update cart set quantity = ? where user_id = ? and product_id = ? ";
    final String selectQuery2 = "select * from cart where user_id = ? and product_id = ?;";
    final String updateQuery2 = "update cart set quantity = ? where user_id = ? and product_id = ?";
    final String addQuery2 = "insert into cart values(?,?,1)";

    public CartDao() {
        connectionService = new ConnectionService();
    }

    public CartDao(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public List<CartProduct> getCart(int userId) throws InternalServerException {
        List<CartProduct> result;
        try {
            Connection conn = connectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement(getQuery);
            statement.setInt(1, userId);
            ResultSet set = statement.executeQuery();
            result = new ArrayList<>();
            while (set.next()) {
                TempProduct product = new TempProduct(set.getInt(1), set.getString(2), set.getInt(3), set.getString(4));
                CartProduct cartProduct = new CartProduct(product, set.getInt(5));
                result.add(cartProduct);
            }
            conn.close();
        } catch (SQLException e) {
            throw new InternalServerException("There was an SQL exception");

        }
        return result;

    }

    public void removeProductFromCart(int userId, int productId) throws InternalServerException {
        try {
            Connection conn = connectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement(selectQuery);
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                int quantity = set.getInt(3);
                if (quantity >= 1) {
                    quantity -= 1;

                    PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, quantity);
                    preparedStatement.setInt(2, userId);
                    preparedStatement.setInt(3, productId);
                    preparedStatement.executeUpdate();
                } else {

                    PreparedStatement statement1 = conn.prepareStatement(removeQuery);
                    statement1.setInt(1, userId);
                    statement1.setInt(2, productId);
                    statement1.execute();
                }
            }
            conn.close();
        } catch (SQLException e) {
            throw new InternalServerException("There was an SQL exception");
        }

    }

    public void addProductInCart(int productId, int userId) throws InternalServerException {
        try {
            Connection conn = connectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement(selectQuery2);
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                int quantity = set.getInt(3);
                quantity += 1;
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery2);
                preparedStatement.setInt(1, quantity);
                preparedStatement.setInt(2, userId);
                preparedStatement.setInt(3, productId);
                preparedStatement.executeUpdate();
            } else {
                PreparedStatement preparedStatement = conn.prepareStatement(addQuery2);
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, productId);
                preparedStatement.executeUpdate();
            }
            conn.close();
        } catch (SQLException e) {
            throw new InternalServerException("There was some problem connecting to the SQL database");
        }
    }
}
