/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment7.controller;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.util.zip.DeflaterOutputStream;

/**
 * This class controls all the postgresql queries and connects to the database and table;
 */
public class StudentController {

    private Scanner scanner;
    private String url;
    private String username;
    private String password;

    public StudentController(String url, String username, String password) {
        this.scanner = new Scanner(System.in);
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * This method compresses the data file generated for ex. my 46 mb file was converted to 11 mb.
     * @throws IOException
     */
    public void compressingFile() throws IOException {
        FileInputStream fis = new FileInputStream("src/main/java/com/zs/assignment7/data.txt");
        FileOutputStream fos = new FileOutputStream("src/main/java/com/zs/assignment7/dataCompress.txt");
        DeflaterOutputStream dos = new DeflaterOutputStream(fos);
        int data;
        while ((data = fis.read()) != -1) {
            dos.write(data);
        }
        fis.close();
        dos.close();
    }

    /**
     * This method verifies whether our connection to the database was successful or not.
     * @return
     */
    public void connectToDatabase() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:2006/student","postgres","root123");
            if (conn != null) {
                System.out.println("connection successfully");
            } else {
                System.out.println("connection error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @throws SQLException
     * @throws IOException
     * This method loads the data and stores the data into text file
     */
    public void loadDataIntoText() throws SQLException, IOException {
        Connection con = DriverManager.getConnection(url, username, password);
        Statement select = con.createStatement();
        BufferedWriter bw = null;
        PreparedStatement preparedStatement = null;
        int start = 0;
        int offset = 10000;
        FileWriter fw = new FileWriter("src/main/java/com/zs/assignment7/data.txt");
        bw = new BufferedWriter(fw);
        while (offset <= 1000000) {

            String query = "select s.*,d.dept_id from students s join department d on s.dept_id=d.dept_id where s.id>? and s.id<=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, offset);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                try {

                    bw.write(result.getInt("id") + " | ");
                    bw.write(result.getString("first")+ " | ");
                    bw.write(result.getString("last") + " | ");
                    bw.write(result.getString("mobile")+ " | ");
                    bw.write(String.valueOf(result.getInt("dept_id")));
                    bw.newLine();
                } catch (Exception e) {
                    System.out.println("something went wrong .." + e);
                }
            }
            System.out.println("data inserted " + offset);
            start = start + 10000;
            offset = offset + 10000;

        }
        bw.close();
        select.close();

    }

    /**
     * This method maps 1,2, 3 dept id with the students table as dept. id acts as a foreign key.
     * @return
     */
    public void updateDepartment() {
        String query = "do $$\n" +
                "begin\n" +
                "   for cnt in 1..1000000 loop\n" +
                "    update students set dept_id=(floor(random() * 3 + 1)) where id=cnt;\n" +
                "   end loop;\n" +
                "end; $$";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Generate the 1 million data into the students table
     */
    public void generateMillionRecord() {
        String query = "with recursive cte as (\n" +
                "   select 0 as ctr\n" +
                "   union all \n" +
                "   select ctr + 1 from cte where ctr < 1000000\n" +
                ")\n" +
                "insert into students (id, first,last, mobile) \n" +
                "select ctr,'first'||cast(ctr as varchar(30)) as first,'last'||cast(ctr as varchar(30)) as last, floor(random()*(1000000)+1) as mobile from cte;";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("inserted records");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * create the students table into the database student.
     */
    public void createStudent() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE TABLE students " +
                    "(id INTEGER not NULL, " +
                    "first VARCHAR(32), " +
                    "last VARCHAR(32), " +
                    "mobile VARCHAR(12), " +
                    "dept_id INTEGER," +
                    "PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created student table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * creates the department table in the database student.
     */
    public void createDepartment() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE TABLE department " +
                    "(dept_id INTEGER not NULL, " +
                    "name VARCHAR(32) not NULL, " +
                    "PRIMARY KEY ( dept_id ));";
            stmt.executeUpdate(sql);
            System.out.println("Created department table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts values in the department table.
     */
    public void insertDepartment() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
        ) {
            String dept_row1 = "INSERT INTO department (dept_id, name) VALUES (1, 'CS');";
            stmt.executeUpdate(dept_row1);
            String dept_row2 = "INSERT INTO department (dept_id, name) VALUES (2, 'EE');";
            stmt.executeUpdate(dept_row2);
            String dept_row3 = "INSERT INTO department (dept_id, name) VALUES (3, 'MECH');";
            stmt.executeUpdate(dept_row3);
            System.out.println("Inserted values");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Links the department table and student table using dept. id as the foreign key.
     */
    public void addForeignKey() {
        String query = "ALTER TABLE students ADD FOREIGN KEY (dept_id) REFERENCES department(dept_id);";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

