/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment5;

import com.zs.assignment5.controller.ParserController;
import com.zs.assignment5.exceptions.FileCannotBeFoundException;
import com.zs.assignment5.exceptions.NotAbleToParseException;

/**
 * This calls the controller class to run the operations.
 */
public class ParserMain {

    public static void main(String[] args) throws NotAbleToParseException, FileCannotBeFoundException {
        ParserController controller= new ParserController();
        controller.run();
    }
}
