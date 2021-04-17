package com.dkit.oopca5.core;

/* The CAOService class has constants to define all of the messages that are sent between the Client and Server
 */
//Name: Sean McAvoy
//Student Number: D00233349
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

    public static final String DISPLAY_ALLCOURSES_COMMAND = "DISPLAY ALL";
    public static final String SUCCESSFULL_DISPLAY_ALLCOURSES = "SUCCESSFUL DISPLAY ALL";
    public static final String DISPLAY_ALLCOURSES_ERROR = "DISPLAY ALL ERROR";

    public static final String DISPLAY_CURRENT_CHOICES_COMMAND = "DISPLAY CURRENT";
    public static final String DISPLAY_CURRENT_CHOICES_ERROR = "DISPLAY CURRENT ERROR";
    public static final String SUCCESSFUL_DISPLAY_CHOICES_CURRENT = "SUCCESSFUL DISPLAY CURRENT";

    public static final String UPDATE_CURRENT_CHOICES_COMMAND = "UPDATE CURRENT";
    public static final String UPDATE_CURRENT_CHOICES_ERROR = "UPDATE CURRENT ERROR";
    public static final String SUCCESSFUL_UPDATE_CHOICES_CURRENT = "SUCCESSFUL UPDATE";







}
