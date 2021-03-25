package com.dkit.oopca5.client;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation
{
    public static int menuOptionValidation()
    {
        int option = -1; //users choice so will repeat again as 0 quits application
        //will try get the users option if the user does'nt type in a int will throw an exception that will be caught
        Boolean isInt = false;
        while(!isInt)
        {
            try{
                Scanner in = new Scanner(System.in);
                System.out.print(">");
                option = in.nextInt();
                if(option != -1)
                {
                    isInt = true;
                }
            }catch(InputMismatchException e) {
                System.out.println("--Please Enter an Integer--");
            }
        }

        return option;
    }
}
