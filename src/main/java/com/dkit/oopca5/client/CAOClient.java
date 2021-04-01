package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;
import com.sun.tools.javac.Main;

import java.util.Scanner;

public class CAOClient
{
    private  static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("Welcome to the CAO Service");
        CAOClient client = new CAOClient();
        client.start();
    }

    private void start()
    {
        //Declare and create socket (using constants from CAOServices)
        //Declare and create Scanner & printWritter objects & pass to method below
        doMainMenuLoop();

    }


    private void doMainMenuLoop()
    {
        printMainMenu();
        MainMenu menuOption = getMainMenuOption();
        while(menuOption != MainMenu.QUIT_APPLICATION)
        {
            switch (menuOption)
            {
                case REGISTER:
                    System.out.println("Register Chosen");
                    register();
                    break;

                case LOGIN:
                    System.out.println("Login");
                    int user = login();
                    if(true)
                    {
                        doLoginMenu(user);
                    }
                    else{
                        //login failed
                    }
                    break;
            }

            printMainMenu();
            menuOption = getMainMenuOption();

        }
    }
    private void printMainMenu()
    {
        for(int i=0; i<MainMenu.values().length; i++)
        {
            System.out.println("\t"+ Colours.BLUE+ i + ". "+ MainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select option (0 to cancel):");

    }
    private MainMenu getMainMenuOption()
    {
        MainMenu menuOption = null;
        boolean validOption = false;
        while(!validOption)
        {
            int option = Validation.menuOptionValidation();
            if(option >= 0 && option < MainMenu.values().length)
            {
                validOption = true;
                menuOption = MainMenu.values()[option];
            }
            else{
                System.out.println("--Valid Options are [0 - 2]--");
            }
        }

        return menuOption;
    }
    private void register()
    {
        System.out.println("In order to Register to CAO we need your CAO number, Date of Birth and Password");

        int caoNumber = RegexChecker.correctCAONumber();
        String dob = RegexChecker.correctDOB();
        System.out.print("Password: ");
        String password = keyboard.next();
        String message = "REGISTER" + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER +
                    dob + CAOService.BREAKING_CHARACTER + password;
        System.out.println("Message ready for server: "+ message);

        /*
        * send Message to server on OUT
        * read respone from Server on IN
        * */
        String response = CAOService.SUCCESSFUL_REGISTER; //Just To mimic Successful response
        if(response.equals(CAOService.SUCCESSFUL_REGISTER))
        {
            System.out.println("Registered ");
        }
        else if(response.equals(CAOService.FAILED_REGISTER))
        {
            System.out.println("There was a problem with the registration");
        }
    }

    private int login()
    {
        int caoNumberReturn = 0;
        int caoNumber = RegexChecker.correctCAONumber();
        String dob = RegexChecker.correctDOB();
        System.out.print("Password: ");
        String password = keyboard.next();
        String message = "LOGIN" + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER +
                dob + CAOService.BREAKING_CHARACTER + password;
        System.out.println("Message ready for server: "+ message);

        /*
         * send Message to server on OUT
         * read respone from Server on IN
         * */
        String response = CAOService.SUCCESSFUL_LOGIN; //Just To mimic Successful response
        if(response.equals(CAOService.SUCCESSFUL_LOGIN))
        {
            System.out.println("LOGGED IN ");
            caoNumberReturn = caoNumber;
        }
        else if(response.equals(CAOService.FAILED_LOGIN))
        {
            System.out.println("LOGIN FAILED");
        }
        return caoNumberReturn;
    }

    private void printLoginMenu()
    {
        for (int i = 0; i < LoginMenu.values().length; i++)
        {
            System.out.println("\t"+ Colours.GREEN+ i +". "+ LoginMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select option:");
    }

    private void doLoginMenu(int user)
    {
        printLoginMenu();
        LoginMenu loginOption = getLoginMenuOption();
        while(loginOption != LoginMenu.QUIT)
        {
            switch (loginOption)
            {
                case LOGOUT:
                    System.out.println("Logged Out");
                    break;
                case DISPLAY_COURSE:

                    break;


            }

        }
    }

    private LoginMenu getLoginMenuOption()
    {
        LoginMenu menuOption = null;
        boolean validOption = false;
        while(!validOption)
        {
            int option = Validation.menuOptionValidation();
            if(option >= 0 && option < LoginMenu.values().length)
            {
                validOption = true;
                menuOption = LoginMenu.values()[option];
            }
            else{
                System.out.println("--Valid Options are [0 - 5]--");
            }
        }

        return menuOption;

    }



}


