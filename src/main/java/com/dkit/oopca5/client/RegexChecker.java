package com.dkit.oopca5.client;

/* This class should contain static methods to verify input in the application
 */

import com.dkit.oopca5.core.Colours;

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
        boolean correctCaoNumber = false;
        do{
            System.out.print("CAO Number: ");
            caoNumber = Validation.menuOptionValidation();
            correctCaoNumber = caoNumberRegExpression(caoNumber);
            if(!correctCaoNumber)
                System.out.println("--CAO Number must be 8 Digits--\n");
        }while(!correctCaoNumber);
        return caoNumber;
    }

    private static boolean courseIDRegExpression(String courseID)
    {
        String regex = "^[a-zA-Z0-9]{5}$"; //2 letters //3numbers
        String input = courseID;
        boolean courseIDOK = input.matches(regex);
        return courseIDOK;
    }
    public static String correctCourseID()
    {
        String courseID;
        boolean correctCourseID = false;
        do{
            System.out.print("Course ID: ");
            courseID = keyboard.next();
            correctCourseID = courseIDRegExpression(courseID);
            if(!correctCourseID)
                System.out.println("--Course ID length of 5--\n");
        }while(!correctCourseID);

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
        boolean correctDOB = false;
        //keyboard.nextLine();
        do{
            System.out.println("DOB (yyyy-mm-dd):");
            DOB = keyboard.nextLine();
            correctDOB = dateRegExpression(DOB);
            if(!correctDOB) //checking the date is in the right format
                System.out.println("--Date format is (yyyy-mm-dd)--");
        }while(!correctDOB);
        return DOB;
    }

    public static boolean passwordRegExpression(String password)
    {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        String input = password;
        boolean correct = input.matches(regex);
        return correct;
    }

    public static String correctPassword()
    {
        String password;
        boolean correctPassword = false;
        //keyboard.nextLine();
        do{
            System.out.println("Password:");
            password = keyboard.nextLine();
            correctPassword = passwordRegExpression(password);
            if(!correctPassword)//checking the date is in the right format
            {
                System.out.println("--Password format --");
                System.out.println("\t"+Colours.RED+"1)Password must contain at least one digit [0-9].");
                System.out.println("\t2)Password must contain at least one lowercase Latin character [a-z].");
                System.out.println("\t3)Password must contain at least one uppercase Latin character [A-Z].");
                System.out.println("\t4)Password must contain a length of at least 8 characters and a maximum of 20 characters."+Colours.RESET);
            }
        }while(!correctPassword);
        return password;
    }

}
