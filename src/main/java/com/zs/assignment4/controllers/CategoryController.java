/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment4.controllers;

import com.zs.assignment4.services.CategoryService;
import java.util.Scanner;

/**
 * This method controls the CategoryService class and calls the operations which the user inputs.
 */
public class CategoryController {
    private  CategoryService categoryOperations;
    private Scanner scanner;
    public CategoryController(){
        categoryOperations = new CategoryService();
        scanner= new Scanner(System.in);
    }
    public void run() {

        System.out.println("Operations on Categories");
        boolean flag;
        int choice;
        do {

            System.out.println("enter your choice to perform the operation on Category\n 1.Display Hierarchy \n 2. Search of a Category \n");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    categoryOperations.displayAll();
                    break;
                case 2:
                    System.out.println("Enter category to search");
                    String category = scanner.next();
                    categoryOperations.search(category);
                    break;
                default:
                    System.out.println("Not a valid choice");
                    break;
            }
            System.out.println("Do you want Continue : ");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Please enter choice");
            int inp = scanner.nextInt();
            if(inp == 1){
                flag = true;
            }
            else{
                System.out.println("End");
                flag = false;
            }
        } while (flag);
    }

}

