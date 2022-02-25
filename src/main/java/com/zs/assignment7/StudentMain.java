/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment7;

import com.zs.assignment7.controller.StudentController;

import java.io.IOException;
import java.sql.SQLException;

public class StudentMain {
    /**
     * Initialize the final state for the connecting to the database.
     */
    public static final String USER = "postgres";
    public static final String PASSWORD = "root123";
    public static final String DATABASE = "student";
    public static final String DB_URL = "jdbc:postgresql://localhost:2006/" + DATABASE;


    public static void main(String[] args) throws SQLException, IOException {

        StudentController controller = new StudentController(DB_URL, USER, PASSWORD);
        controller.connectToDatabase();
        controller.createStudent();
        controller.createDepartment();
        controller.insertDepartment();
        controller.addForeignKey();
        controller.generateMillionRecord();
        controller.updateDepartment();
        controller.loadDataIntoText();
        controller.compressingFile();

    }
}