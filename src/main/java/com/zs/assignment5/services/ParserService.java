/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment5.services;

import com.zs.assignment5.controller.ParserController;
import com.zs.assignment5.exceptions.IncompleteInformationException;
import com.zs.assignment5.exceptions.IncorrectFileFormatException;
import com.zs.assignment5.exceptions.NotAbleToParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

/**
 * This class has 3 main services: get total count, count each day and users not committed in 2 days
 */
public class ParserService {
    Date date;
    static Logger logger = LogManager.getLogger(ParserController.class.getName());
    Scanner scanner;
    File file;

    public ParserService() {
        file = new File("/home/raramuri/Desktop/ZS_Assignments/src/main/java/com/zs/assignment5/gitlog.txt");
        try {
            scanner = new Scanner(file);
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        } catch (FileNotFoundException | ParseException e) {
            logger.error("The file is not found");
            System.exit(0);
        }
    }

    /**
     * Divides the log File in words so that they can be used.
     * @return
     * @throws IncorrectFileFormatException
     * @throws IncompleteInformationException
     */
    public ArrayList<String[]> getWords() throws IncorrectFileFormatException, IncompleteInformationException {
        ArrayList<String> log = new ArrayList<>();
        while (scanner.hasNextLine()) {
            log.add(scanner.nextLine());
        }
        ArrayList<String[]> data = new ArrayList<>();
        for (String line : log) {
            String[] words = line.split("@");
            data.add(words);
            if (words.length != 3) throw new IncorrectFileFormatException("File format not correct");
            logger.info("-----------------------------------------------------");
            for (String ans : words) {
                if (ans.length() == 0) throw new IncompleteInformationException("Incomplete information in file");
            }
        }
        return data;
    }

    /**
     * Gets the count of total Commits.
     * @throws IncorrectFileFormatException
     * @throws IncompleteInformationException
     * @throws NotAbleToParseException
     */
    public void getCountOfTotalCommits() throws IncorrectFileFormatException, IncompleteInformationException, NotAbleToParseException {
        ArrayList<String[]> data = getWords();
        HashMap<String, Integer> result = new HashMap<>();
        for (String[] commit : data) {
            Date commitDate;
            try {
                commitDate = new SimpleDateFormat("yyyy-MM-dd").parse(commit[1]);
            } catch (ParseException e) {
                throw new NotAbleToParseException("Cannot Parse");
            }
            if (date.compareTo(commitDate) <= 0) {
                result.put(commit[2], result.getOrDefault(commit[2], 0) + 1);
            }

        }
        logger.info(result);
    }

    /**
     * Gets the count of commits each day.
     * @throws IncorrectFileFormatException
     * @throws IncompleteInformationException
     * @throws NotAbleToParseException
     */
    public void getCountOfCommitsEachDay() throws IncorrectFileFormatException, IncompleteInformationException, NotAbleToParseException {
        ArrayList<String[]> data = getWords();
        HashMap<String, HashMap<String, Integer>> result = new HashMap<>();
        for (String[] eachCommitData : data) {
            Date commitDate;
            String user = eachCommitData[2];
            String logDate = eachCommitData[1];

            try {
                commitDate = new SimpleDateFormat("yyyy-MM-dd").parse(logDate);
            } catch (ParseException e) {
                throw new NotAbleToParseException("Cannot Parse");
            }

            if (date.compareTo(commitDate) <= 0) {
                result.putIfAbsent(user, new HashMap<>());
                result.get(user).put(logDate, result.get(user).getOrDefault(logDate, 0) + 1);
            }
        }
        logger.info(result);

    }

    /**
     * Gets a list of users who committed in less than 2 days.
     * @throws NotAbleToParseException
     * @throws IncorrectFileFormatException
     * @throws IncompleteInformationException
     */
    public void getList() throws NotAbleToParseException, IncorrectFileFormatException, IncompleteInformationException {
        int days = 2;
        ArrayList<String[]> data = getWords();
        List<String> result = new ArrayList<>();
        for (String[] eachCommitData : data) {
            Date commitDate;
            String user = eachCommitData[2];
            try {
                commitDate = new SimpleDateFormat("yyyy-MM-dd").parse(eachCommitData[1]);
            } catch (ParseException e) {
                throw new NotAbleToParseException("Cannot Parse");
            }
            assert commitDate != null;
            long diff = date.getTime() - commitDate.getTime();
            long differenceInDays = (diff / (1000 * 60 * 60 * 24)) % 365;
            if (differenceInDays >= days) {
                result.add(user);
            }

        }
        logger.info(result);
    }
}