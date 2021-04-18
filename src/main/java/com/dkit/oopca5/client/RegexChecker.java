package com.dkit.oopca5.client;

//Name: Sean McAvoy
//Student Number: D00233349

/* This class should contain static methods to verify input in the application
 */

import com.dkit.oopca5.core.Colours;

import java.util.Scanner;

/**
 * All Reg Ex validation
 */
public class RegexChecker
{
    private  static Scanner keyboard = new Scanner(System.in);

    /**
     * caoNumberRegExpression()
     * makes sure CAO number is 8 digits
     * @param caoNumber of the student
     * @return true if CAO number is 8 Digits
     * @return false if CAO number isnt 8 Digits
     */
    private static boolean caoNumberRegExpression(int caoNumber)
    {
        String regex = "\\d{8}";
        String input = Integer.toString(caoNumber);
        boolean caoNumberOK = input.matches(regex);
        return caoNumberOK;
    }

    /**
     * correctCAONumber()
     * gets the user to type in the CAO number and validates it
     * @return the caoNumber when its valid
     */
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

    /**
     * courseIDRegExpression(String courseID)
     * make sure all courseID are 2 letter and 3 Digits
     * @param courseID the id of the course
     * @return true / false depending if the ID is correct or not
     */
    private static boolean courseIDRegExpression(String courseID)
    {
        String regex = "[a-zA-Z]{2}\\d{3}"; //2 letters //3numbers
        String input = courseID;
        boolean courseIDOK = input.matches(regex);
        return courseIDOK;
    }

    /**
     * correctCourseID()
     * will ask the user for courseID input and validate it
     * @return the courseID when its valid
     */
    public static String correctCourseID()
    {
        String courseID;
        boolean correctCourseID = false;
        do{
            System.out.print("Course ID: ");
            courseID = keyboard.next();
            correctCourseID = courseIDRegExpression(courseID);
            if(!correctCourseID)
                System.out.println("--Course ID length of 5 (Two letters followed by 3 numbers)--\n");
        }while(!correctCourseID);

        return courseID;
    }

//    private static boolean courseLevelRegExpression(String courseLevel)
//    {
//        String regex = "^[7-9]{1}||[0-1]{2}$";
//        String input = courseLevel;
//        boolean correct = input.matches(regex);
//        return correct;
//    }

    /**
     * dateRegExpression(String date)
     * Ask user to type in the date and will validate it
     * @param date date in format of yyyy-mm-dd
     * @return the date when its valid
     */
    private static boolean dateRegExpression(String date)
    {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        String input = date;
        boolean correct = input.matches(regex);
        return correct;
    }

    /**
     * correctDOB()
     * will make sure the date format is correct
     * @return the date when valid format
     */
    public static String correctDOB()
    {
        String DOB;
        boolean correctDOB = false;
        //keyboard.nextLine();
        do{
            System.out.println("DOB (yyyy-mm-dd):");
            DOB = keyboard.next();
            correctDOB = dateRegExpression(DOB);
            if(!correctDOB) //checking the date is in the right format
                System.out.println("--Date format is (yyyy-mm-dd)--");
        }while(!correctDOB);
        return DOB;
    }

    /**
     * passwordRegExpression(String password)
     * validates password is strong enough
     * @param password users password
     * @return password when meets all the requirements
     */
    public static boolean passwordRegExpression(String password)
    {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        String input = password;
        boolean correct = input.matches(regex);
        return correct;
    }

    /**
     * correctPassword()
     * ask user to type in the password and will continue to ask till valid
     * @return valid Password
     */
    public static String correctPassword()
    {
        String password;
        boolean correctPassword = false;
        //keyboard.nextLine();
        do{
            System.out.println("Password:");
            password = keyboard.next();
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
