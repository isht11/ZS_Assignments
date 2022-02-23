/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment5.controller;

import com.zs.assignment5.services.ParserService;
import com.zs.assignment5.exceptions.IncorrectFileFormatException;
import com.zs.assignment5.exceptions.IncompleteInformationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This class runs the 3 operations by taking inputs from the user.
 */
public class ParserController {

    public void run(Date date) {
        Scanner scanner=null;
        ArrayList<String> log = new ArrayList<>();
        File file=new File("/home/raramuri/Desktop/ZS_Assignments/src/main/java/com/zs/assignment5/gitlog.txt");
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                log.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<String[]> data= new ArrayList<>();
        for(String line:log) {
            String[] word = line.split("@");
            data.add(word);
            try {
                if (word.length != 3) throw new IncorrectFileFormatException("File format not correct");
                System.out.println("-----------------------------------------------------");
                for (String ans : word) {
                    if (ans.length() == 0) throw new IncompleteInformationException("Incomplete information in file");
                }
            } catch (IncorrectFileFormatException | IncompleteInformationException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        Scanner scan= new Scanner(System.in);
        ParserService parserService = new ParserService();
        boolean flag;
        int choice;
        do {

            System.out.println("1: Total Count of commits by each developer since date d" + "\n" + "2: Count of commits by each developer since date d, for each day" + "\n" + "3: List of developers who did not commit anything successively in 2 days" + "\n" + "0: Exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    parserService.getCount1(data, date);
                    break;
                case 2:
                    parserService.getCount2(data, date);
                    break;
                case 3:
                    parserService.getList(data, new Date(), 2);
                    break;
                default:
                    System.out.println("Not a valid choice");
                    break;
            }
            System.out.println("Do you want Continue : ");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Please enter choice");
            int inp = scan.nextInt();
            if(inp == 1){
                flag = true;
            }else{
                System.out.println("End");
                flag = false;
            }
        } while (flag);
    }
}
