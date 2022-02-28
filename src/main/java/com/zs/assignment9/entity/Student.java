/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9.entity;

/**
 * Blueprint of the student class.
 */
public class Student {
    private Integer student_id;
    private String firstname;
    private String lastname;


    public int getStudent_id() {
        return student_id;
    }

    public Student(Integer student_id, String firstname, String lastname) {
        this.student_id = student_id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public Student(){}

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
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