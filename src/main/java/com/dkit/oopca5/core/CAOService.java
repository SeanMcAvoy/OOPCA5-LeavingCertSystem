package com.dkit.oopca5.core;

/* The CAOService class has constants to define all of the messages that are sent between the Client and Server
 */

public class CAOService
{
    //
    public static final int PORT_NUM = 50025;
    public static final String HOSTNAME = "localhost";

    public static final String BREAKING_CHARACTER = "%%";

    public static final String REGISTER_COMMAND = "REGISTER";
    public static final String SUCCESSFUL_REGISTER = "REGISTERED";
    public static final String FAILED_REGISTER = "REG FAILED";
    public static final String CAO_Number_Duplicate = "Duplicate CAO number";

    public static final String LOGIN_COMMAND = "LOGIN";
    public static final String SUCCESSFUL_LOGIN = "LOGGED IN";
    public static final String FAILED_LOGIN = "LOGIN FAILED";

    public static final String DISPLAY_COURSE_COMMAND = "DISPLAY COURSE";
    public static final String DISPLAY_COURSE_ERROR = "DISPLAY COURSE ERROR";




    // need the to create:
    // Logout Commands
    // DISPLAY COURSE commands
    // displayAll commands
    // Display current choices command
    // Update choices commands


}
