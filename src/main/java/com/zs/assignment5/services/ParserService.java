/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment5.services;

import com.zs.assignment5.exceptions.NotAbleToParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class has 3 main services: get total count, count each day and users not commited in 2 days
 */
public class ParserService {
    /**
     * This method displays the total commits but each user.
     *
     * @param data
     * @param date
     */
    public void getCountOfTotalCommits(ArrayList<String[]> data, Date date) throws NotAbleToParseException {

        HashMap<String, Integer> result = new HashMap<>();

        for (String[] commit : data) {

            Date commitDate = null;

            try {
                commitDate = new SimpleDateFormat("yyyy-MM-dd").parse(commit[1]);
            } catch (ParseException e) {
                throw new NotAbleToParseException("Cannot Parse");
            }

            if (date.compareTo(commitDate) <= 0) {
                result.put(commit[2], result.getOrDefault(commit[2], 0) + 1);
            }

        }

        System.out.println(result);
    }

    /**
     * This method displays the commit each day by a user.
     *
     * @param data
     * @param date
     */
    public void getCountOfCommitsEachDay(ArrayList<String[]> data, Date date) throws NotAbleToParseException {

        HashMap<String, HashMap<String, Integer>> result = new HashMap<>();
        for (String[] eachCommitData : data) {
            Date commitDate = null;
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
        System.out.println(result);

    }

    /**
     * This method displays the users who have not committed since 2 days given a date.
     *
     * @param commitsData
     * @param date
     * @param days
     */
    public void getList(ArrayList<String[]> commitsData, Date date, int days) throws NotAbleToParseException {

        List<String> result = new ArrayList<>();

        for (String[] eachCommitData : commitsData) {
            Date commitDate = null;
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
        System.out.println(result);

    }

}