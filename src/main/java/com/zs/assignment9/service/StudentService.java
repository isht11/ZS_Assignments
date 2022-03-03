/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9.service;

import com.zs.assignment9.dao.StudentDao;
import com.zs.assignment9.entity.Student;
import com.zs.assignment9.exceptions.ThisIsMyException;

/**
 * Connects the student dao class to the controller.
 */
public class StudentService {

    private StudentDao studentDao;
    public StudentService(){

        this.studentDao = new StudentDao();
    }
    public StudentService(StudentDao studentDao){

        this.studentDao = studentDao;
    }

    /**
     * sends the student object to the student dao class to create it in the database.
     * @param input
     */
    public void createStudent(Student input) throws ThisIsMyException {
        studentDao.save(input);
    }

    /**
     * Sends the id of the student to the student dao class to fetch the information.
     * @param id
     */
    public void getStudent(Integer id) throws ThisIsMyException {
        studentDao.getById(id);

    }


}