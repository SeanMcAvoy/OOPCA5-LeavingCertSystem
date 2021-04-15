package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;
import com.dkit.oopca5.core.Course;
import com.sun.tools.javac.Main;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CAOClient
{
    private  static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args)
    {
        CAOClient client = new CAOClient();
        client.start();
        System.out.println("\nGood bye");
    }

    private void start()
    {
        //Declare and create socket (using constants from CAOServices)
        //Declare and create Scanner & printWritter objects & pass to method below
        Scanner in = new Scanner(System.in);
        try{
            Socket socket = new Socket(CAOService.HOSTNAME, 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );
            System.out.println("Client message: The Client is running and has connected to the server");
            OutputStream os = socket.getOutputStream();
            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            System.out.println("\nWelcome to the CAO Service");
            doMainMenuLoop(socketWriter,socketReader);

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }

    }

    private void doMainMenuLoop(PrintWriter socketWriter, Scanner socketReader)
    {
        printMainMenu();
        MainMenu menuOption = getMainMenuOption();
        while(menuOption != MainMenu.QUIT_APPLICATION)
        {
            switch (menuOption)
            {
                case REGISTER:
                    System.out.println("Register Chosen");
                    register(socketWriter,socketReader);
                    break;
                case LOGIN:
                    System.out.println("Login");
                    login(socketWriter,socketReader);
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
    private void register(PrintWriter socketWriter, Scanner socketReader)
    {
        System.out.println("In order to Register to CAO we need your CAO number, Date of Birth and Password");

        int caoNumber = RegexChecker.correctCAONumber();
        String dob = RegexChecker.correctDOB();
        //System.out.print("Password: ");
        String password = RegexChecker.correctPassword();
        String message = CAOService.REGISTER_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER +
                    dob + CAOService.BREAKING_CHARACTER + password;
        //System.out.println("Message ready for server: "+ message);

        /*
        * send Message to server on OUT
        * read respone from Server on IN
        * */
        socketWriter.println(message);
        String response = socketReader.nextLine();
        if(response.equals(CAOService.SUCCESSFUL_REGISTER))
        {
            System.out.println("Registered");
        }
        else if(response.equals(CAOService.FAILED_REGISTER))
        {
            System.out.println("There was a problem with the registration");
        }
        else if(response.equals(CAOService.CAO_Number_Duplicate))
        {
            System.out.println("CAO number is already Taken");
        }
    }

    private void login(PrintWriter socketWriter, Scanner socketReader)
    {
        int caoNumber = RegexChecker.correctCAONumber();
        String dob = RegexChecker.correctDOB();
        System.out.print("Password: ");
        String password = keyboard.next();
        String message = CAOService.LOGIN_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER +
                dob + CAOService.BREAKING_CHARACTER + password;

        socketWriter.println(message);
        String response = socketReader.nextLine();
        if(response.equals(CAOService.SUCCESSFUL_LOGIN))
        {
            System.out.println("LOGGED IN ");
            doLoginMenu(caoNumber,socketWriter,socketReader);
        }
        else if(response.equals(CAOService.FAILED_LOGIN))
        {
            System.out.println("LOGIN FAILED");
        }
    }

    private void printLoginMenu()
    {
        for (int i = 0; i < LoginMenu.values().length; i++)
        {
            System.out.println("\t"+ Colours.GREEN+ i +". "+ LoginMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select option:");
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

    private void doLoginMenu(int user,PrintWriter socketWriter, Scanner socketReader)
    {
        printLoginMenu();
        LoginMenu loginOption = getLoginMenuOption();
        while(loginOption != LoginMenu.LOGOUT)
        {
            switch (loginOption)
            {
                case QUIT:
                    System.out.println("\nGood bye");
                    System.exit(0);
                    break;
                case DISPLAY_COURSE:
                    displayCourse(socketWriter,socketReader);
                    break;
                case DISPLAY_ALL_COURSES:
                    displayAllCourses(socketWriter,socketReader);
                    break;
                case DISPLAY_CURRENT_CHOICES:
                    if(user != 0)
                        displayCurrentChoices(user,socketWriter,socketReader);
                    else System.out.println("Must be login");
                    break;
                case UPDATE_CURRENT_CHOICES:
                    if(user != 0)
                        updateCurrentChoices(user,socketWriter,socketReader);
                    else System.out.println("Must be login");
                    break;
            }
            printLoginMenu();
            loginOption = getLoginMenuOption();
        }
    }
    private void displayCourse(PrintWriter socketWriter, Scanner socketReader)
    {
        System.out.println("Course ID of the course the course you would like to see");
        String courseID = RegexChecker.correctCourseID();
        String message = CAOService.DISPLAY_COURSE_COMMAND + CAOService.BREAKING_CHARACTER + courseID;
        //System.out.println("Message ready for server: "+ message);

        socketWriter.println(message);
        String response = socketReader.nextLine();
        if(response.equals(CAOService.DISPLAY_COURSE_ERROR)){
            System.out.println("No course with That ID");
        }else{
            System.out.println(response);
            String[] components = message.split(CAOService.BREAKING_CHARACTER);
            courseID = components[0];
            String level = components[1];
            String courseTitle = components[2];
            String college = components[4];
            Course c = new Course(courseID,level,courseTitle,college);
            System.out.println(c);
        }






    }
    private void displayAllCourses(PrintWriter socketWriter, Scanner socketReader)
    {
        String message = CAOService.DISPLAY_ALLCOURSES_COMMAND;
        //System.out.println("Message ready for server: "+ message);
        socketWriter.println(message);
        String response = socketReader.nextLine();
        String[] components = response.split(CAOService.BREAKING_CHARACTER);
        if(components[0].equals(CAOService.DISPLAY_COURSE_ERROR))
        {
            System.out.println("There is No Courses");
        }
        else if(components[0].equals(CAOService.SUCCESSFULL_DISPLAY_ALLCOURSES))
        {
            List<Course> courses = new ArrayList<>();
            int count = 0;
            String courseID="",level ="",courseTitle ="",college ="";
            for(int i = 1; i< components.length; i++)
            {
                if(count == 0)
                {
                    courseID = components[i];
                    count++;
                }
                else if(count == 1)
                {
                    level = components[i];
                }
                else if(count == 2)
                {
                    courseTitle = components[i];
                }
                else if(count == 3)
                {
                    college = components[i];
                }
                else if(count == 4)
                {
                    count = 0;
                    Course c = new Course(courseID,level,courseTitle,college);
                    courses.add(c);
                }
                if(count != 0)
                    count++;
            }
            displayAllCourses(courses);
        }

    }
    private void displayCurrentChoices(int user,PrintWriter socketWriter, Scanner socketReader)
    {
        String message = CAOService.DISPLAY_CURRENT_CHOICES_COMMAND + CAOService.BREAKING_CHARACTER + user;
        //System.out.println("Message ready for server: "+ message);
        socketWriter.println(message);
        String response = socketReader.nextLine();
        String[] components = response.split(CAOService.BREAKING_CHARACTER);
        if(components[0].equals(CAOService.SUCCESSFUL_DISPLAY_CHOICES_CURRENT))
        {
            System.out.println("Your Choices are:");
            for(int i = 1; i<components.length; i++)
            {
                System.out.println(i+")"+components[i]);
            }
        }else if(components[0].equals(CAOService.DISPLAY_CURRENT_CHOICES_ERROR))
        {
            System.out.println("User has no Choices picked");
        }
    }

    private void updateCurrentChoices(int user,PrintWriter socketWriter, Scanner socketReader)
    {
        List<String> choices = new ArrayList<>();
        System.out.println("Enter your choices in preference from first to last.");
        boolean notFinished = true;
        while(notFinished)
        {
            String courseID = RegexChecker.correctCourseID();
            choices.add(courseID);
            System.out.println("Do you wish to enter another?");
            notFinished = Validation.yesNoValidation();
        }
        String message = CAOService.UPDATE_CURRENT_CHOICES_COMMAND + CAOService.BREAKING_CHARACTER + user;
        for (String c : choices)
        {
            message += CAOService.BREAKING_CHARACTER + c;
        }
        //System.out.println("Message ready for server: "+ message);
        socketWriter.println(message);
        String response = socketReader.nextLine();
        String[] components = response.split(CAOService.BREAKING_CHARACTER);
        if(components[0].equals(CAOService.SUCCESSFUL_UPDATE_CHOICES_CURRENT))
        {
            System.out.println("Choices Updated");
        }
        else if(components[0].equals(CAOService.UPDATE_CURRENT_CHOICES_ERROR)){
            System.out.println("Error occurred");
        }
    }
    private static void displayAllCourses(List<Course> courses)
    {
        String format = "%-10s%-8s %-50s%-30s\n";
        System.out.printf(format,"CourseID","Level","Title","Institute");
        for(Course c : courses)
            System.out.printf(format,c.getCourseId(),c.getLevel(),c.getTitle(),c.getInstitution());
        System.out.println();
    }
    private static void displayCourse(Course c)
    {
        System.out.println("Course Info:");
        System.out.println("CourseID:'"+c.getCourseId()+"', Level:'"+c.getLevel()+"', Title:'"+c.getTitle()+"', Institute:'"+c.getInstitution()+"'");
        System.out.println();
    }







}


