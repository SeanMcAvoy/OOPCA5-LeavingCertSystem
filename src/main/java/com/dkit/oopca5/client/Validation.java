package com.dkit.oopca5.client;

import java.util.InputMismatchException;
import java.util.Scanner;
//Name: Sean McAvoy
//Student Number: D00233349
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

    public static Boolean yesNoValidation()
    {
        Boolean answer = null;
        try
        {
            boolean correct = false;
            while(!correct)
            {
                Scanner in = new Scanner(System.in);
                System.out.print("Answer: ");
                String ans = in.next();
                if(ans.equalsIgnoreCase("Yes"))
                {
                    correct = true;
                    answer = true;
                }
                else if(ans.equalsIgnoreCase("No"))
                {
                    correct = true;
                    answer =false;
                }
                else {
                    System.out.println("Error - Valid input is [Yes/No]");
                }
            }
        }catch(InputMismatchException e) {
            System.out.println("--Please Enter an String--");
        }
        return answer;

    }

    
}
