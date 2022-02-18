/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment3;

import com.zs.assignment3.controller.ProductController;

/**
 * This class calls the controller to run the project.
 */
public class ProductCatalogueMain {
    /**
     * This is the main method.
     * @param args
     */
    public static void main(String[] args) {
        ProductController controller = new ProductController();
        controller.run();
    }
}
