/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment2;

import java.util.Scanner;

/**
 * This class performs all the operations on the matrices.
 */
public class MatrixMain {
    /**
     * This takes in two user defined matrices and calls all the operations.
     * @param args
     */
    public static void main(String[] args) {
        MatrixComputations matrixServices = new MatrixComputations();
        Scanner scanner = new Scanner(System.in);
        Matrix matrix1 = readMatrix();
        Matrix matrix2 = readMatrix();
        System.out.print("\n");
        boolean flag;
        int choice;
        do {

            System.out.println("enter your choice to perform operations on matrices\n 1.Add matrices \n 2. Subtract matrices \n 3.Multiply matrices \n 4. Multiply matrix by k \n 5.Transpose matrix \n ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Matrix added=matrixServices.addMatrices(matrix1, matrix2);
                    if(added!=null){
                        printMatrix(added);
                    }
                    break;
                case 2:
                    Matrix subtracted=matrixServices.subMatrices(matrix1, matrix2);
                    if(subtracted!=null){
                        printMatrix(subtracted);
                    }
                    break;
                case 3:
                    Matrix multiplied=matrixServices.mulMatrices(matrix1, matrix2);
                    if(multiplied!=null){
                        printMatrix(multiplied);
                    }
                    break;
                case 4:
                    System.out.println("Enter k : ");
                    int k= scanner.nextInt();
                    Matrix kMultiplied=matrixServices.kMatrixMultiply(matrix1, k);
                    printMatrix(kMultiplied);
                    break;
                case 5:
                    Matrix transposed=matrixServices.transpose(matrix1);
                    printMatrix(transposed);
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

    /**
     * This method reads a user given matrix and returns it.
     * @return
     */
    public static Matrix readMatrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Rows for matrix: ");
        int rows = scanner.nextInt();
        System.out.println("Enter Columns for matrix: ");
        int columns = scanner.nextInt();
        Matrix input=new Matrix(rows, columns);
        System.out.println("Enter the elements of the matrix");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                input.data[i][j] = scanner.nextInt();
            }
        }
        return input;
    }

    /**
     * This method prints the given matrix.
     * @param output
     */
    public static void printMatrix(Matrix output){
        for(int i=0;i<output.row;i++){
            for(int j=0;j<output.column;j++){
                System.out.print(output.data[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
