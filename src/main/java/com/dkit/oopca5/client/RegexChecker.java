package com.dkit.oopca5.client;

/* This class should contain static methods to verify input in the application
 */

import java.util.Scanner;

public class RegexChecker
{
    private  static Scanner keyboard = new Scanner(System.in);

    private static boolean caoNumberRegExpression(int caoNumber)
    {
        String regex = "\\d{8}";
        String input = Integer.toString(caoNumber);
        boolean caoNumberOK = input.matches(regex);
        return caoNumberOK;
    }
    public static int correctCAONumber()
    {
        int caoNumber;
        do{
            System.out.print("CAO Number: ");
            caoNumber = keyboard.nextInt();
            if(!caoNumberRegExpression(caoNumber))
                System.out.println("--CAO Number must be 8 Digits--\n");
        }while(!caoNumberRegExpression(caoNumber));
        return caoNumber;

    }

    private static boolean courseIDRegExpression(String courseID)
    {
        String regex = "^[a-zA-Z0-9]{5}$";
        String input = courseID;
        boolean courseIDOK = input.matches(regex);
        return courseIDOK;
    }
    public static String correctCourseID()
    {
        String courseID;
        do{
            System.out.print("Course ID: ");
            courseID = keyboard.next();
            if(!courseIDRegExpression(courseID))
                System.out.println("--Course ID length of 5--\n");
        }while(!courseIDRegExpression(courseID));
        return courseID;
    }

    private static boolean courseLevelRegExpression(String courseLevel)
    {
        String regex = "^[7-9]{1}||[0-1]{2}$";
        String input = courseLevel;
        boolean correct = input.matches(regex);
        return correct;
    }

    private static boolean dateRegExpression(String date)
    {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        String input = date;
        boolean correct = input.matches(regex);
        return correct;
    }

    public static String correctDOB()
    {
        String DOB;
        keyboard.nextLine();
        do{
            System.out.println("DOB (yyyy-mm-dd):");
            DOB = keyboard.nextLine();
            if(!RegexChecker.dateRegExpression(DOB)) //checking the date is in the right format
                System.out.println("--Date format is (yyyy-mm-dd)--");
        }while(!dateRegExpression(DOB));
        return DOB;
    }

}
