/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment7;

import com.zs.assignment7.controller.StudentController;
import com.zs.assignment7.exceptions.NotConnectedException;

public class StudentMain {

    public static void main(String[] args) throws NotConnectedException {

        StudentController controller = new StudentController();
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