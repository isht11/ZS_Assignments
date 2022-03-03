/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment7.controller;

import com.zs.assignment7.exceptions.NotConnectedException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.zip.DeflaterOutputStream;

/**
 * This class controls all the postgresql queries and connects to the database and table;
 */
public class StudentController {

    private Scanner scanner;

    public StudentController() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * This method compresses the data file generated for ex. my 46 mb file was converted to 11 mb.
     *
     * @return
     * @throws NotConnectedException
     */
    public void compressingFile() throws NotConnectedException {
        try {
            FileInputStream fis = new FileInputStream("src/main/java/com/zs/assignment7/data.txt");
            FileOutputStream fos = new FileOutputStream("src/main/java/com/zs/assignment7/dataCompress.txt");
            DeflaterOutputStream dos = new DeflaterOutputStream(fos);
            int data;
            while ((data = fis.read()) != -1) {
                dos.write(data);
            }
            fis.close();
            dos.close();
        } catch (IOException e) {
            throw new NotConnectedException("It is an IO exception");
        }
    }

    /**
     * This method verifies whether our connection to the database was successful or not.
     *
     * @return
     */
    public Connection connectToDatabase() throws NotConnectedException {
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:2006/student", "postgres", "root123");
        } catch (Exception e) {
            throw new NotConnectedException("Could Not Connect");
        }
        return conn;
    }

    /**
     * @throws SQLException
     * @throws IOException  This method loads the data and stores the data into text file
     */
    public void loadDataIntoText() throws NotConnectedException {
        Connection con = this.connectToDatabase();
        BufferedWriter bw;
        PreparedStatement preparedStatement;
        int offset = 10000;
        FileWriter fw;
        try {
            fw = new FileWriter("src/main/java/com/zs/assignment7/data.txt");
        } catch (IOException e) {
            throw new NotConnectedException("There is an error");
        }
        bw = new BufferedWriter(fw);
        while (offset <= 1000000) {

            String query = "select s.*,d.dept_id from students s join department d on s.dept_id=d.dept_id LIMIT 10000 OFFSET ?";
            ResultSet result;
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, offset);
                result = preparedStatement.executeQuery();
                con.close();
                while (result.next()) {

                    bw.write(result.getInt("id") + " | ");
                    bw.write(result.getString("first") + " | ");
                    bw.write(result.getString("last") + " | ");
                    bw.write(result.getString("mobile") + " | ");
                    bw.write(String.valueOf(result.getInt("dept_id")));
                    bw.newLine();
                }
            } catch (IOException | SQLException e) {
                throw new NotConnectedException("This is a SQL exception");
            }
            offset = offset + 10000;
            try {
                bw.close();
            } catch (IOException e) {
                throw new NotConnectedException("It is an IO exception");
            }
        }
    }

    /**
     * @return Generate the 1 million data into the students table
     */
    public void generateMillionRecord() throws NotConnectedException {
        final String query = "with recursive cte as (\n" +
                "   select 0 as ctr\n" +
                "   union all \n" +
                "   select ctr + 1 from cte where ctr < 1000000\n" +
                ")\n" +
                "insert into students (id, first,last, mobile) \n" +
                "select ctr,'first'||cast(ctr as varchar(30)) as first,'last'||cast(ctr as varchar(30)) as last, floor(random()*(1000000)+1) as mobile from cte;";
        try {
            Connection connection = this.connectToDatabase();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException | NotConnectedException e) {
            throw new NotConnectedException("It is an exception");
        }
    }

    /**
     * create the students table into the database student.
     */
    public void createStudent() throws NotConnectedException {
        final String query = "CREATE TABLE students " +
                "(id INTEGER not NULL, " +
                "first VARCHAR(32), " +
                "last VARCHAR(32), " +
                "mobile VARCHAR(12), " +
                "dept_id INTEGER," +
                "PRIMARY KEY ( id ))";
        try {
            Connection conn = this.connectToDatabase();
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        } catch (SQLException | NotConnectedException e) {
            throw new NotConnectedException("It is an exception");
        }

    }

    /**
     * creates the department table in the database student.
     */
    public void createDepartment() throws NotConnectedException {
        final String query = "CREATE TABLE department " +
                "(dept_id INTEGER not NULL, " +
                "name VARCHAR(32) not NULL, " +
                "PRIMARY KEY ( dept_id ));";
        try {
            Connection conn = this.connectToDatabase();
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        } catch (SQLException | NotConnectedException e) {
            throw new NotConnectedException("It is an exception");
        }
    }

    /**
     * Inserts values in the department table.
     */
    public void insertDepartment() throws NotConnectedException {
        final String deptRow1 = "INSERT INTO department (dept_id, name) VALUES (1, 'CS');";
        final String deptRow2 = "INSERT INTO department (dept_id, name) VALUES (2, 'EE');";
        final String deptRow3 = "INSERT INTO department (dept_id, name) VALUES (3, 'MECH');";
        try {
            Connection conn = this.connectToDatabase();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(deptRow1);
            stmt.executeUpdate(deptRow2);
            stmt.executeUpdate(deptRow3);
            conn.close();
        } catch (SQLException | NotConnectedException e) {
            throw new NotConnectedException("It is an exception");
        }
    }

    /**
     * Links the department table and student table using dept. id as the foreign key.
     */
    public void addForeignKey() throws NotConnectedException {
        final String query = "ALTER TABLE students ADD FOREIGN KEY (dept_id) REFERENCES department(dept_id);";
        try {
            Connection connection = this.connectToDatabase();
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } catch (SQLException | NotConnectedException e) {
            throw new NotConnectedException("This is SQL error");
        }
    }

    public void updateDepartment() throws NotConnectedException {
        final String query = "do $$\n" +
                "begin\n" +
                "   for cnt in 1..1000000 loop\n" +
                "    update students set dept_id=(floor(random() * 3 + 1)) where id=cnt;\n" +
                "   end loop;\n" +
                "end; $$";
        try {
            Connection connection = this.connectToDatabase();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            throw new NotConnectedException("This is SQL error");
        }
    }
}

