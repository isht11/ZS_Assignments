/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9.controller;

import com.zs.assignment9.entity.Student;
import com.zs.assignment9.service.StudentService;

import java.util.Scanner;

/**
 * Runs the get details and create student functions.
 */
public class StudentController {


    private StudentService  studentService;
    private Scanner scanner;

    /**
     * Constructor that creates a new object of the service class.
     */
    public StudentController(){
        studentService = new StudentService();
        scanner= new Scanner(System.in);
    }


    public void run()
    {
        boolean flag  = true;

        do{

            System.out.println("\n 1. add student \n 2. get student by id");
            int choice  =  scanner.nextInt();
            switch (choice){
                case 1:
                    this.addStudent();
                    break;
                case 2:
                    this.getStudentById();
                    break;
                default:
                    System.out.println("Enter character for continue or stop");
                    char ch  =  scanner.next().charAt(0);
                    System.out.println("\n Y for continue \n N for stop");
                    if(ch=='Y'){
                        flag =  true;
                    }else{
                        flag =  false;
                    }
            }


        }while(flag==true);

    }

    /**
     * Gets the details of the student by passing the id.
     */
    private void getStudentById() {

        System.out.println("Enter student ID to get student");
        Integer id  =  scanner.nextInt();
        studentService.getStudent(id);
    }

    /**
     * Creates a new entry in the database by passing the details entered by the user.
     */
    private void addStudent()
    {
        System.out.println("Enter student Id");
        Integer id = scanner.nextInt();
        System.out.println("Enter student firstName");
        String firstName = scanner.next();
        System.out.println("Enter student lastName");
        String lastName  =  scanner.next();
        Student input= new Student(id , firstName , lastName);
        this.studentService.createStudent(input);
    }
}