package com.zs.assignment13.dao;

import com.zs.assignment13.ConnectionService;
import com.zs.assignment13.entity.Category;
import com.zs.assignment13.exceptions.InternalServerException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    ConnectionService connectionService;
    final String selectQuery = "select * from category";
    final String addQuery = "insert into category values(?,?);";
    final String deleteQuery = "delete from category where category_id=?";
    final String updateQuery = "update category set name = ? where category_id = ?";
    final String existQuery = "SELECT COUNT(*) FROM category WHERE category_id = ?";

    public CategoryDao() {
        connectionService = new ConnectionService();
    }

    public CategoryDao(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public List<Category> getAllCategories() throws InternalServerException {

        List<Category> result = new ArrayList<>();
        try {
            Connection conn = connectionService.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(selectQuery);
            while (rs.next()) {
                Category category = new Category(rs.getInt(1), rs.getString(2));
                result.add(category);
            }
        } catch (SQLException e) {
            throw new InternalServerException("There is an sql exception");
        }
        return result;
    }

    public void addCategory(Category category) throws InternalServerException {
        try {
            Connection conn = connectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement(addQuery);
            statement.setInt(1, category.getId());
            statement.setString(2, category.getName());
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new InternalServerException("There is an SQL exception");
        }
    }

    public void deleteCategory(int id) throws InternalServerException {
        try {
            Connection conn = connectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1, id);
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new InternalServerException("There is an SQL exception");
        }
    }

    public void updateCategory(int id, Category category) throws InternalServerException {
        try {
            Connection conn = connectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setString(1, category.getName());
            statement.setInt(2, id);
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new InternalServerException("There is an SQL exception");
        }
    }

    public boolean isExist(int id) throws InternalServerException {
        ResultSet resultSet;
        try {
            Connection con = connectionService.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(existQuery);
            preparedStatement.setInt(1, id);
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


}
