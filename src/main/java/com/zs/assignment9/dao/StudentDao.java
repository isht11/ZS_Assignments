/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9.dao;

import com.zs.assignment9.entity.Student;
import com.zs.assignment9.exceptions.ThisIsMyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Connects to the database and creates and fetches values.
 */
public class StudentDao {
    private static final String URL = "jdbc:postgresql://localhost:2006/student";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root123";
    static Logger logger = LogManager.getLogger(StudentDao.class.getName());

    /**
     * Database Connection
     *
     * @return
     */
    public Connection connectionToDatabase() throws ThisIsMyException, SQLException {
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ThisIsMyException("This is an error");
        }
        return conn;
    }

    /**
     * Puts the values in the student table
     *
     * @param student
     */
    public void save(Student student) throws ThisIsMyException {
        Statement statement;
        final String QUERY = "INSERT INTO student2 VALUES (" + student.getStudentId() + "," + student.getFirstname() + " ," + student.getLastname() + ");";
        try {
            Connection con = this.connectionToDatabase();
            statement = con.createStatement();
            statement.executeUpdate(QUERY);
            con.close();
        } catch (SQLException e) {
            throw new ThisIsMyException("This is an exception");
        }
    }

    /**
     * Gets the student details by passing the id of the student.
     *
     * @param id
     */
    public void getById(Integer id) throws ThisIsMyException {
        final String QUERY = "SELECT * FROM student2 WHERE id=" + id;
        Statement statement;
        try {
            Connection con = this.connectionToDatabase();
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                logger.info(resultSet.getInt("id"));
                logger.info(resultSet.getString("firstname"));
                logger.info(resultSet.getString("lastname"));
            }

        } catch (SQLException e) {
            throw new ThisIsMyException("This is an error");
        }
    }
}