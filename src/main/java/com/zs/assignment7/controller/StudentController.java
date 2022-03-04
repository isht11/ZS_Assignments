/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment7.controller;

import com.zs.assignment7.exceptions.InternalServerException;
import com.zs.assignment7.services.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Calls the service class to deal with the queries.
 */
public class StudentController {

    private final StudentService studentService;
    static Logger logger = LogManager.getLogger(StudentController.class.getName());

    public StudentController() {

        studentService = new StudentService();
    }

    public void run() {

        try {
            studentService.createStudent();
            studentService.createDepartment();
            studentService.insertDepartment();
            studentService.addForeignKey();
            studentService.generateMillionRecord();
            studentService.updateDepartment();
            studentService.loadDataIntoText();
            studentService.compressingFile();
        } catch (InternalServerException e) {
            logger.error(e.getMessage());
            System.exit(0);
        }

    }
}

