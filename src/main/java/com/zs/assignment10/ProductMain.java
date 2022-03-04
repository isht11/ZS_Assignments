/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10;

import com.zs.assignment10.controller.ProductController;

/**
 * Calls the controller
 */
public class ProductMain {

    public static void main(String[] args)  {

        ProductController controller = new ProductController();
        controller.run();
    }
}