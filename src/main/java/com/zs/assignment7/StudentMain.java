/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment7;

import com.zs.assignment7.controller.StudentController;
import com.zs.assignment7.exceptions.InternalServerException;

/**
 * Runs the controller class.
 */
public class StudentMain {

    public static void main(String[] args) throws InternalServerException {

        StudentController controller = new StudentController();
        controller.run();
    }
}