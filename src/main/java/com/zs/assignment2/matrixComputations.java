/**
 * @author : Ishtmeet Singh Arora
 * This file performs matrix computations on any two user entered matrices.
 */
package com.zs.assignment2;

import java.util.Scanner;

public class matrixComputations {

        /**
         * @param a  Matrix
         * This Method displays any matrix that is passsed.
         */

        public void printmatrix(matrix a){
            int rows=a.row;
            int columns=a.column;
            for(int i=0;i< rows;i++){
                for(int j=0;j< columns;j++){
                    System.out.print(a.data[i][j] + " ");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }

        /**
         * @param a  Matrix 1
         * @param b Matrix 2
         * This method adds 2 matrices that are passed and if they do not have equal no. rows and columns then displays an error message.
         */

        public void addMatrices(matrix a, matrix b){
            int columns1=a.column;
            int columns2=b.column;
            int rows1=a.row;
            int rows2=b.row;
            if((rows1 !=rows2) || (columns1 != columns2)){
                System.out.print("Matrix Cannot be Added\n");
            }
            else {
                matrix added= new matrix(rows1, columns1);
                for (int i = 0; i < rows1; i++) {
                    for (int j = 0; j < columns1; j++) {
                        added.data[i][j] = a.data[i][j] + b.data[i][j];
                    }
                }
                printmatrix(added);
            }
        }

        /**
         * @param a  Matrix 1
         * @param b  Matrix 2
         * This method subtracts 2 matrices that are passed and if they do not have equal no. rows and columns then displays an error message.
         */
        public void subMatrices(matrix a, matrix b){
            int columns1=a.column;
            int columns2=b.column;
            int rows1=a.row;
            int rows2=b.row;
            if((rows1 !=rows2) || (columns1 != columns2)){
                System.out.print("Matrix Cannot be Subtracted\n");
            }
            else {
                matrix subtracted = new matrix(rows1, columns1);
                for (int i = 0; i < rows1; i++) {
                    for (int j = 0; j < columns1; j++) {
                        subtracted.data[i][j] = a.data[i][j] - b.data[i][j];
                    }
                }
                printmatrix(subtracted);
            }
        }

        /**
         * @param a  Matrix 1
         * @param b  Matrix 2
         * This method multiplies 2 matrices that are passed and if the no. of columns of matrix A is not equal to the rows of matrix B then displays an error message.
         */

        public void mulMatrices(matrix a, matrix b){
            int columns1=a.column;
            int columns2=b.column;
            int rows1=a.row;
            int rows2=b.row;
            if(rows2 != columns1){
                System.out.print("Matrix Cannot be Multiplied\n");
            }
            else {
                matrix multiplied = new matrix(rows1, columns2);
                for (int i = 0; i < columns1; i++) {
                    for (int j = 0; j < b.column; j++) {
                        for (int k = 0; k < rows1; k++) {
                            multiplied.data[i][j] += a.data[i][k] * b.data[k][j];
                        }
                    }
                }
                printmatrix(multiplied);
            }
        }

        /**
         * @param a Matrix 1
         * @param k integer k
         * This method multiplies the matrix with the integer k.
         */
        public void kmatrixMultiply(matrix a,int k){
            int columns=a.column;
            int rows=a.row;
            matrix kmultiplied =new matrix(rows, columns);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    kmultiplied.data[i][j]=a.data[i][j]*k;
                }
            }
            printmatrix(kmultiplied);
        }

    /**
     * This Method gets the value of k to be multiplied to a matrix.
     * @return
     */
    public int fetchK(){

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter k : ");
            int k= scanner.nextInt();
            return k;
        }

    /**
     * Transposing of a matrix means the interchanging of it's rows and columns.
     * @param a Matrix 1
     * This method transposes the matrix 1.
     */
    public  void transpose(matrix a){
            int rows=a.row;
            int columns=a.column;
            matrix b = new matrix (columns, rows);
            for(int i=0;i<columns;i++){
                for(int j=0;j<rows;j++){
                    b.data[i][j] = a.data[j][i];
                }
            }
            printmatrix(b);
        }

    /**
     * @return Matrix
     * This method readsor gets the input matrix from the user.
     */
    public matrix readMatrix(matrix a) {
            int rows=a.row;
            int columns=a.column;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the elements of the matrix");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    a.data[i][j] = scanner.nextInt();
                }
            }
            return a;
        }

    }

