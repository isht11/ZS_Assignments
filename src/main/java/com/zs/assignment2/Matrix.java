/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment2;

/**
 * This class contains the blueprint of the matrix.
 */
public class Matrix {

    int row;
    int column;
    int [][]data;

    /**
     * This is a constructor that initializes the matrix row, column and data.
     * @param row rows of the matrix
     * @param column columns of the matrix
     */
    public Matrix(int row, int column){
        this.row=row;
        this.column=column;
        data=new int[row][column];
    }
}
