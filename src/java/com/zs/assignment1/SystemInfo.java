package src.java.com.zs.assignment1;
import java.io.*;
import java.io.File;
import java.util.*;

/**
 * This java file displays System Information.
 */

public class SystemInfo {

    /**
     * Created a main function to display username, user home directory, Memory,  cores, name and version of OS.
     */

    public static void main(String[] args) {
        String username = System.getProperty("user.name");
        String userHomeDir = System.getProperty("user.home");
        Runtime Memory = Runtime.getRuntime();
        String nameOfOs = System.getProperty("os.name");
        String versionOfOS = System.getProperty("sun.arch.data.model");
        long file = new File("/home").getUsableSpace();
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("username = " + username);
        System.out.println("The User Home Directory is "+userHomeDir);
        System.out.println("Total memory is: " + Memory.totalMemory());
        System.out.println("Total no. of cores are: " + cores);
        System.out.println("Total Space is: " + file);
        System.out.println("Name of the operating system is: " + nameOfOs);
        System.out.println("Version of the operating system is: " + versionOfOS);
    }
}
