package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

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


    }

}


