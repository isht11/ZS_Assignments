/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment5;

import com.zs.assignment5.controller.ParserController;

/**
 * This calls the controller class to run the operations.
 */
public class ParserMain {

    public static void main(String[] args) {
        ParserController controller = new ParserController();
        controller.run();
    }

}
