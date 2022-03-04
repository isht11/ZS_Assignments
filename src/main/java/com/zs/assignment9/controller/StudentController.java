/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9.controller;

import com.zs.assignment9.entity.Student;
import com.zs.assignment9.exceptions.InternalServerException;
import com.zs.assignment9.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Runs the get details and create student functions.
 */
public class StudentController {


    private StudentService studentService;
    private Scanner scanner;
    static Logger logger = LogManager.getLogger(StudentController.class.getName());

    /**
     * Constructor that creates a new object of the service class.
     */
    public StudentController() {
        studentService = new StudentService();
        scanner = new Scanner(System.in);
    }


    public void run() {
        boolean flag = true;
        try {
            do {

                logger.info("\n 1. add student \n 2. get student by id");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        this.addStudent();
                        break;
                    case 2:
                        this.getStudentById();
                        break;
                    default:
                        logger.info("Enter character for continue or stop");
                        char ch = scanner.next().charAt(0);
                        logger.info("\n Y for continue \n N for stop");
                        if (ch == 'Y') {
                            flag = true;
                        } else {
                            flag = false;
                        }
                }

            } while (flag);
        } catch (InternalServerException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Gets the details of the student by passing the id.
     */
    private void getStudentById() throws InternalServerException {

        logger.info("Enter student ID to get student");
        Integer id = scanner.nextInt();
        studentService.getStudent(id);
    }

    /**
     * Creates a new entry in the database by passing the details entered by the user.
     */
    private void addStudent() throws InternalServerException {
        logger.info("Enter student Id");
        Integer id = scanner.nextInt();
        logger.info("Enter student firstName");
        String firstName = scanner.next();
        logger.info("Enter student lastName");
        String lastName = scanner.next();
        Student input = new Student(id, firstName, lastName);
        studentService.createStudent(input);
    }
}