/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.dao;

import com.zs.assignment11.dbConnection.DBConnection;
import com.zs.assignment11.exceptions.InternalServerError;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * connects to the database and has methods to insert, update, delete to the table.
 */
public class CategoryDao {
    DBConnection dbConnection;
    public CategoryDao(){
        dbConnection = new DBConnection();
    }
    public CategoryDao(DBConnection dbConnection){
        this.dbConnection = dbConnection;
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
            Connection con = dbConnection.connectionToDatabase();
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
