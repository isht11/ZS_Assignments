/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment4;

import com.zs.assignment4.controllers.CategoryController;

/**
 * This class calls the controller that runs the operations.
 */
public class CategoryMain {

    public static void main(String[] args) {
        CategoryController controller = new CategoryController();
        controller.run();
    }
}
