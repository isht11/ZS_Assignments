/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment2;

import java.util.Scanner;

/**
 * This class performs all the operations on the matrices.
 */
public class main {
    public static void main(String[] args) {
        int rows, columns;
        matrixComputations matrixServices = new matrixComputations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Rows for matrix 1: ");
        rows = scanner.nextInt();
        System.out.println("Enter Columns for matrix 1: ");
        columns = scanner.nextInt();
        matrix matrix1 = new matrix(rows, columns);
        matrix1 = matrixServices.readMatrix(matrix1);
        System.out.println("Enter Rows for matrix 2: ");
        int rowsfor2nd = scanner.nextInt();
        System.out.println("Enter Columns for matrix 2: ");
        int columnsfor2nd = scanner.nextInt();
        matrix matrix2 = new matrix(rowsfor2nd, columnsfor2nd);
        matrix2 = matrixServices.readMatrix(matrix2);
        System.out.print("\n");
        boolean flag = false;
        int choice;
        do {

            System.out.println("enter your choice to perform operations on matrices\n 1.Add matrices \n 2. Subtract matrices \n 3.Multiply matrices \n 4. Multiply matrix by k \n 5.Transpose matrix \n ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    matrixServices.addMatrices(matrix1, matrix2);
                    break;
                case 2:
                    matrixServices.subMatrices(matrix1, matrix2);
                    break;
                case 3:
                    matrixServices.mulMatrices(matrix1, matrix2);
                    break;
                case 4:
                    int k = matrixServices.fetchK();
                    matrixServices.kmatrixMultiply(matrix1, k);
                    break;
                case 5:
                    matrixServices.transpose(matrix1);
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
            if (inp == 1) {
                flag = true;
            } else {
                System.out.println("End");
                flag = false;
            }
        } while (flag);
    }
}
