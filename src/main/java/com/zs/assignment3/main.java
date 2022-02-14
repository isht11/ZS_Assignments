/**
 * @author: Ishtmeet Singh Arora
 */
package src.main.java.com.zs.assignment3;
import src.main.java.com.zs.assignment3.Controller.Productcontroller;

/**
 * This class calls the controller to run the project.
 */
public class main {
    /**
     * This is the main method.
     * @param args
     */
    public static void main(String[] args) {
        Productcontroller controller = new Productcontroller();
        controller.run();
    }
}
