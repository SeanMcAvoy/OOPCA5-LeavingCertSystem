package com.dkit.oopca5.client;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation
{
    public static int menuOptionValidation()
    {
        int option = -1; //users choice so will repeat again as 0 quits application
        //will try get the users option if the user does'nt type in a int will throw an exception that will be caught
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter option: ");
            option = in.nextInt();
        }catch(InputMismatchException e) {
            System.out.println("--Please Enter an Integer--");
        }
        return option;
    }
}
