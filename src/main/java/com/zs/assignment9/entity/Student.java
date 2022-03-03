/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9.entity;

/**
 * Blueprint of the student class.
 */
public class Student {
    private Integer studentId;
    private String firstname;
    private String lastname;

    public Student(Integer studentId, String firstname, String lastname) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public Student(){}

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}