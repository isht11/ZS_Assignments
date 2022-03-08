/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment5.controller;

import com.zs.assignment5.exceptions.IncompleteInformationException;
import com.zs.assignment5.exceptions.IncorrectFileFormatException;
import com.zs.assignment5.exceptions.NotAbleToParseException;
import com.zs.assignment5.services.ParserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * This class runs the 3 operations by taking inputs from the user.
 */
public class ParserController {
    static Logger logger = LogManager.getLogger(ParserController.class.getName());
    ParserService parserService;
    Scanner scanner;

    public ParserController() {
        parserService = new ParserService();
        scanner = new Scanner(System.in);
    }

    public void run() {

        boolean flag;
        int choice;
        try {
            do {
                logger.info("1: Total Count of commits by each developer since date d" + "\n" + "2: Count of commits by each developer since date d, for each day" + "\n" + "3: List of developers who did not commit anything successively in 2 days" + "\n" + "0: Exit");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        parserService.getCountOfTotalCommits();
                        break;
                    case 2:
                        parserService.getCountOfCommitsEachDay();
                        break;
                    case 3:
                        parserService.getList();
                        break;
                    default:
                        logger.info("Not a valid choice");
                        break;
                }
                logger.info("Do you want Continue : ");
                logger.info("1. Yes");
                logger.info("2. No");
                logger.info("Please enter choice");
                int inp = scanner.nextInt();
                if (inp == 1) {
                    flag = true;
                } else {
                    logger.info("End");
                    flag = false;
                }
            } while (flag);
        } catch (IncorrectFileFormatException | IncompleteInformationException | NotAbleToParseException e) {
            logger.error(e.getMessage());
            System.exit(0);
        }
    }
}