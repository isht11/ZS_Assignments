/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment5;

import com.zs.assignment5.controller.ParserController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This calls the controller class to run the operations.
 */
public class ParserMain {

    public static void main(String[] args) throws ParseException {
        ParserController controller= new ParserController();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        controller.run(date);
    }
}
