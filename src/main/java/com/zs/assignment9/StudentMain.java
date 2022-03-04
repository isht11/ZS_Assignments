/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9;

import com.zs.assignment9.controller.StudentController;

/**
 * Calls the run method in the controller class.
 */
public class StudentMain {


    public static void main(String[] args) {
        StudentController studentController = new StudentController();
        studentController.run();
    }
}