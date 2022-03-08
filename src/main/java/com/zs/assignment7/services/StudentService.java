/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment7.services;

import com.zs.assignment7.dao.StudentDao;
import com.zs.assignment7.exceptions.InternalServerException;

/**
 * Calls the dao method to deal with the queries.
 */
public class StudentService {
    private StudentDao studentDao;

    public StudentService() {

        this.studentDao = new StudentDao();
    }

    public void compressingFile() throws InternalServerException {
        studentDao.compressingFile();
    }

    public void createStudent() throws InternalServerException {
        studentDao.createStudent();
    }

    public void addForeignKey() throws InternalServerException {
        studentDao.addForeignKey();
    }

    public void createDepartment() throws InternalServerException {
        studentDao.createDepartment();
    }

    public void generateMillionRecord() throws InternalServerException {
        studentDao.generateMillionRecord();
    }

    public void insertDepartment() throws InternalServerException {
        studentDao.insertDepartment();
    }

    public void loadDataIntoText() throws InternalServerException {
        studentDao.loadDataIntoText();
    }

    public void updateDepartment() throws InternalServerException {
        studentDao.updateDepartment();
    }


}
