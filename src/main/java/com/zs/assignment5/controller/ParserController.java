/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment5.controller;

import com.zs.assignment5.exceptions.FileCannotBeFoundException;
import com.zs.assignment5.exceptions.IncompleteInformationException;
import com.zs.assignment5.exceptions.IncorrectFileFormatException;
import com.zs.assignment5.exceptions.NotAbleToParseException;
import com.zs.assignment5.services.ParserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This class runs the 3 operations by taking inputs from the user.
 */
public class ParserController {
    static Logger logger = LogManager.getLogger(ParserController.class.getName());
    public void run() throws NotAbleToParseException, FileCannotBeFoundException {
        Scanner scanner;
        ArrayList<String> log = new ArrayList<>();
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        } catch (ParseException e) {
            throw new NotAbleToParseException("Cannot parse");
        }
        File file = new File("/home/raramuri/Desktop/ZS_Assignments/src/main/java/com/zs/assignment5/gitlog.txt");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new FileCannotBeFoundException("Cannot find");
        }
        while (scanner.hasNextLine()) {
            log.add(scanner.nextLine());
        }
        ArrayList<String[]> data = new ArrayList<>();
        for (String line : log) {
            String[] words = line.split("@");
            data.add(words);
            try {
                if (words.length != 3) throw new IncorrectFileFormatException("File format not correct");
            } catch (IncorrectFileFormatException e) {
                e.getMessage();
            }
            System.out.println("-----------------------------------------------------");
            for (String ans : words) {
                try {
                    if (ans.length() == 0) throw new IncompleteInformationException("Incomplete information in file");
                } catch (IncompleteInformationException e) {
                    e.getMessage();
                }
            }
            Scanner scan = new Scanner(System.in);
            ParserService parserService = new ParserService();
            boolean flag;
            int choice;
            do {

                logger.info("1: Total Count of commits by each developer since date d" + "\n" + "2: Count of commits by each developer since date d, for each day" + "\n" + "3: List of developers who did not commit anything successively in 2 days" + "\n" + "0: Exit");
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        parserService.getCountOfTotalCommits(data, date);
                        break;
                    case 2:
                        parserService.getCountOfCommitsEachDay(data, date);
                        break;
                    case 3:
                        parserService.getList(data, new Date(), 2);
                        break;
                    default:
                        logger.info("Not a valid choice");
                        break;
                }
                logger.info("Do you want Continue : ");
                logger.info("1. Yes");
                logger.info("2. No");
                logger.info("Please enter choice");
                int inp = scan.nextInt();
                if (inp == 1) {
                    flag = true;
                } else {
                    logger.info("End");
                    flag = false;
                }
            } while (flag);
        }
    }
}