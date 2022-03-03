/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9;

import com.zs.assignment9.controller.StudentController;
import com.zs.assignment9.exceptions.ThisIsMyException;

/**
 * Calls the run method in the controller class.
 */
public class StudentMain {


    public static void main(String[] args) throws ThisIsMyException {
        StudentController studentController = new StudentController();
        studentController.run();
    }
}