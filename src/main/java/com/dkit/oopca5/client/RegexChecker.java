package com.dkit.oopca5.client;

/* This class should contain static methods to verify input in the application
 */

public class RegexChecker
{
    public static boolean caoNumberRegExpression(int caoNumber)
    {
        String regex = "\\d{8}";
        String input = Integer.toString(caoNumber);
        boolean caoNumberOK = input.matches(regex);
        return caoNumberOK;
    }

    public static boolean courseIDRegExpression(String courseID)
    {
        String regex = "^[a-zA-Z0-9]{5}$";
        String input = courseID;
        boolean courseIDOK = input.matches(regex);
        return courseIDOK;
    }
    public static boolean courseLevelRegExpression(String courseLevel)
    {
        String regex = "^[7-9]{1}||[0-1]{2}$";
        String input = courseLevel;
        boolean correct = input.matches(regex);
        return correct;
    }

    public static boolean dateRegExpression(String date)
    {
        String regex = "^\\d{2}-\\d{2}-\\d{4}$";
        String input = date;
        boolean correct = input.matches(regex);
        return correct;
    }

}
