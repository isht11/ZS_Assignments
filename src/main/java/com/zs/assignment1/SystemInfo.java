package com.zs.assignment1;

import java.io.File;

/**
 * @author Ishtmeet Singh Arora
 * This java file displays System Information.
 */

public class SystemInfo {

    /**
     * Created a main function to display username, user home directory, Memory,  cores, name and version of OS.
     * @return
     */

    public static void main(String[] args) {
        String userName = System.getProperty("user.name");
        String userHomeDir = System.getProperty("user.home");
        Runtime memory = Runtime.getRuntime();
        String nameOfOs = System.getProperty("os.name");
        String versionOfOs = System.getProperty("sun.arch.data.model");
        long file = new File("/home").getUsableSpace();
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("username = " + userName);
        System.out.println("The User Home Directory is "+ userHomeDir);
        System.out.println("Total memory is: " + memory.totalMemory());
        System.out.println("Total no. of cores are: " + cores);
        System.out.println("Total Space is: " + file);
        System.out.println("Name of the operating system is: " + nameOfOs);
        System.out.println("Version of the operating system is: " + versionOfOs);
    }
}
