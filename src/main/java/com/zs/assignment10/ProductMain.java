/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10;

import com.zs.assignment10.controller.ProductController;
import com.zs.assignment10.exceptions.InternalServerError;
import com.zs.assignment10.exceptions.NotValidException;
import com.zs.assignment10.exceptions.ProductNotFoundError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Calls the controller
 */
public class ProductMain {
    static Logger logger = LogManager.getLogger(ProductController.class.getName());
    public static void main(String[] args)  {

        ProductController controller = new ProductController();
        try {
            controller.run();
        } catch (InternalServerError | ProductNotFoundError | NotValidException e) {
            logger.error(e.getMessage());
        }
    }
}