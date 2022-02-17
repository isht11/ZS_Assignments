/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment3;

import com.zs.assignment3.controller.productController;

/**
 * This class calls the controller to run the project.
 */
public class main {
    /**
     * This is the main method.
     * @param args
     */
    public static void main(String[] args) {
        productController controller = new productController();
        controller.run();
    }
}
