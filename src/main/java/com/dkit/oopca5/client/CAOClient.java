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
        String dob = "";
        String password = "";
        boolean allInfoComplete = false;

        System.out.println("In order to Register to CAO we need your CAO number, Date of Birth and Password");
        System.out.print("CAO Number: ");
        int caoNumber = keyboard.nextInt();
        if(RegexChecker.caoNumberRegExpression(caoNumber))
        {
            boolean dobFormatCorrect = false;
            keyboard.nextLine();
            while(!dobFormatCorrect)
            {

                System.out.println("DOB (yyyy-mm-dd):");
                dob = keyboard.nextLine();
                if(RegexChecker.dateRegExpression(dob))
                    dobFormatCorrect = true;
                else{
                    System.out.println("--Date format is (yyyy-mm-dd)--");
                }
            }
            System.out.print("Password: ");
            password = keyboard.next();
            allInfoComplete = true;
        }
        else{
            System.out.println("CAO Number must be 8 Digits\n");

        }
        if(allInfoComplete)
        {
            String message = "REGISTER" + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER +
                    dob + CAOService.BREAKING_CHARACTER + password;
            System.out.println("Message ready for server: "+ message);
        }

    }

}


