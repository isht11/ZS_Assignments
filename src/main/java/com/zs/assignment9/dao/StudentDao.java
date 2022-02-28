/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9.dao;

import com.zs.assignment9.entity.Student;

import java.sql.*;

/**
 * Connects to the database and creates and fetches values.
 */
public class StudentDao {
    private static String URL =  "jdbc:postgresql://localhost:2006/student";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "root123";

    /**
     * Database Connection
     * @return
     */
    public Connection connectionToDatabase()
        {
        Connection conn = null;

        try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(URL , USERNAME , PASSWORD);
        if(conn!=null){
        System.out.println("connection successfully");
        }
        else{
        System.out.println("connection error");
        }
        }  catch (Exception e) {
        e.printStackTrace();
        }

        return conn;
        }

    /**
     * Puts the values in the student table
     * @param student
     */
    public void save(Student student) {
        Connection con = this.connectionToDatabase();

        Statement statement = null;
        try {
        statement = con.createStatement();
        String query = "INSERT INTO student2 VALUES (" + student.getStudent_id() + "," + student.getFirstname() + " ," + student.getLastname() + ");";
        statement.executeUpdate(query);
        }
        catch (SQLException e) {
            System.out.println("Something went wrong..");
        }
    }

    /**
     * Gets the student details by passing the id of the student.
     * @param id
     */
    public void getById(Integer id) {
        Connection con = this.connectionToDatabase();
        Statement statement = null;
        try {
        String query = "SELECT * FROM student2 WHERE id="+id;
        statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
        {
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("firstname"));
            System.out.println(resultSet.getString("lastname"));
        }

        } catch (SQLException e) {
        System.out.println("Something went wrong..");
        }
    }

}