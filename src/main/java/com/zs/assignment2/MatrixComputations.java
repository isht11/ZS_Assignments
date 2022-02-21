/**
 * @author : Ishtmeet Singh Arora
 * This file performs matrix computations on any two user entered matrices.
 */
package com.zs.assignment2;

public class MatrixComputations {

        /**
         * @param first  Matrix 1
         * @param second Matrix 2
         * This method adds 2 matrices that are passed and if they do not have equal no. rows and columns then displays an error message.
         */
        public Matrix addMatrices(Matrix first, Matrix second){

            if((first.row != second.row) || (first.column != second.column)){
                System.out.print("Matrix Cannot be Added\n");
                return null;
            }
            else {
                Matrix added= new Matrix(first.row , first.column);
                for (int i = 0; i < first.row; i++) {
                    for (int j = 0; j < first.column; j++) {
                        added.data[i][j] = first.data[i][j] + second.data[i][j];
                    }
                }
                return added;
            }
        }

        /**
         * @param first  Matrix 1
         * @param second  Matrix 2
         * This method subtracts 2 matrices that are passed and if they do not have equal no. rows and columns then displays an error message.
         */
        public Matrix subMatrices(Matrix first, Matrix second){

            if((first.row != second.row) || (first.column != second.column)){
                System.out.print("Matrix Cannot be Subtracted\n");
                return null;
            }
            else {
                Matrix subtracted= new Matrix(first.row , first.column);
                for (int i = 0; i < first.row; i++) {
                    for (int j = 0; j < first.column; j++) {
                        subtracted.data[i][j] = first.data[i][j] - second.data[i][j];
                    }
                }
                return subtracted;
            }
        }

        /**
         * @param first  Matrix 1
         * @param second Matrix 2
         * This method multiplies 2 matrices that are passed and if the no. of columns of matrix A is not equal to the rows of matrix B then displays an error message.
         */

        public Matrix mulMatrices(Matrix first, Matrix second){

            if(second.row != first.column){
                System.out.print("Matrix Cannot be Multiplied\n");
                return null;
            }
            else {
                Matrix multiplied = new Matrix(first.row, second.column);
                for (int i = 0; i < first.row; i++) {
                    for (int j = 0; j < second.column; j++) {
                        for (int k = 0; k < second.row; k++) {
                            multiplied.data[i][j] += first.data[i][k] * second.data[k][j];
                        }
                    }
                }
                return multiplied;
            }
        }

        /**
         * @param input Matrix 1
         * @param k integer k
         * This method multiplies the matrix with the integer k.
         */
        public Matrix kMatrixMultiply(Matrix input, int k){

            Matrix kMultiplied =new Matrix(input.row, input.column);
            for (int i = 0; i < input.row; i++) {
                for (int j = 0; j < input.column; j++) {
                    kMultiplied.data[i][j]=input.data[i][j]*k;
                }
            }
            return kMultiplied;
        }

        /**
         * Transposing of a matrix means the interchanging of its rows and columns.
         * @param input Matrix 1
         * This method transposes the matrix 1.
         */
        public Matrix transpose(Matrix input){

                Matrix output = new Matrix(input.column, input.row);
                for(int i=0;i<input.column;i++){
                    for(int j=0;j<input.row;j++){
                        output.data[i][j] = input.data[j][i];
                    }
                }
                return output;
            }


        }

