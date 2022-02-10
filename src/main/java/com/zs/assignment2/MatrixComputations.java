package src.main.java.com.zs.assignment2;
import java.util.Scanner;

/**
 * @author : Ishtmeet Singh Arora
 * This file performs matrix computations on any two user entered matrices.
 */

public class MatrixComputations {

        /**
         * @param a  Matrix
         * This Method displays any matrix that is passsed.
         */

        static void printmatrix(int a[][]){
            int rows=a.length;
            int cols=a[0].length;
            for(int i=0;i< rows;i++){
                for(int j=0;j< cols;j++){
                    System.out.print(a[i][j] + " ");
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

        static void addmatrices(int a[][], int b[][]){
            int columns=a[0].length;
            int rows=a.length;
            if((a.length !=b.length) || (a[0].length!=b[0].length)){
                System.out.print("Matrix Cannot be Added\n");
            }
            else {
                int added[][] = new int[rows][columns];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        added[i][j] = a[i][j] + b[i][j];
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
        static void submatrices(int a[][], int b[][]){
            int columns=a[0].length;
            int rows=a.length;
            if((a.length !=b.length) || (a[0].length!=b[0].length)){
                System.out.print("Matrix Cannot be Subtracted\n");
            }
            else {
                int subtracted[][] = new int[rows][columns];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        subtracted[i][j] = a[i][j] - b[i][j];
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

        static void mulmatrices(int a[][], int b[][]){
            int columns=a.length;
            int rows=b[0].length;
            if(rows != columns){
                System.out.print("Matrix Cannot be Multiplied\n");
            }
            else {
                int multiplied[][] = new int[rows][columns];
                for (int i = 0; i < a[0].length; i++) {
                    for (int j = 0; j < b[0].length; j++) {
                        for (int k = 0; k < a.length; k++) {
                            multiplied[i][j] += a[i][k] * b[k][j];
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
        static void kmatrixmultiply(int a[][],int k){
            int columns=a[0].length;
            int rows=a.length;
            int kmultiplied[][]=new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    kmultiplied[i][j]=a[i][j]*k;
                }
            }
            printmatrix(kmultiplied);
        }

    /**
     * This Method gets the value of k to be multiplied to a matrix.
     * @return
     */
    static int fetchk(){

            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter k : ");
            int k= myObj.nextInt();
            return k;
        }

    /**
     * Transposing of a matrix means the interchanging of it's rows and columns.
     * @param a Matrix 1
     * This method transposes the matrix 1.
     */
    static void transpose(int a[][]){
            int rows=a.length;
            int cols=a[0].length;
            int b[][]=new int [cols][rows];
            for(int i=0;i<cols;i++){
                for(int j=0;j<rows;j++){
                    b[i][j] = a[j][i];
                }
            }
            printmatrix(b);
        }

    /**
     * @return Matrix
     * This method readsor gets the input matrix from the user.
     */
    public static int [][] readmatrix() {
            int rows, columns;
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter Rows : ");
            rows = myObj.nextInt();
            System.out.println("Enter Columns : ");
            columns = myObj.nextInt();
            int matrix[][] = new int[rows][columns];
            System.out.println("Enter the elements of the matrix");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = myObj.nextInt();
                }
            }
            return matrix;
        }

    /**
     * @param args
     * This file calls all the matrix operations to be performed.
     */
        public static void main(String[] args) {
            int matrix1[][]=readmatrix();
            int matrix2[][]=readmatrix();
            System.out.print("\n");
            addmatrices(matrix1, matrix2);
            submatrices(matrix1, matrix2);
            mulmatrices(matrix1, matrix2);
            int k=fetchk();
            kmatrixmultiply(matrix1,k);
            transpose(matrix1);
        }



    }

