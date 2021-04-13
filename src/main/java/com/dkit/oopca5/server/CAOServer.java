package com.dkit.oopca5.server;

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler
 thread to deal with that connection. The server then returns to listening
 */

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CAOServer
{

    public static void main(String[] args)
    {
        CourseDaoInterface ICourseDAO = new MySqlCourseDAO();
        StudentDaoInterface IStudentDAO = new MySqlStudentDAO();
        CourseChoiceDaoInterface ICourseChoiceDAO = new MySqlCourseChoice();
        CAOServer server = new CAOServer();
        server.start();
//
//        try
//        {
//            System.out.println("getAllCourse()");
//            List<Course> courses = ICourseDAO.findAllCourses();
//            if(courses.isEmpty())
//            {
//                System.out.println("There is No Courses");
//            } else{
//                displayAllCourses(courses);
//            }
//
//            System.out.println("\ngetCourseByID(DN100)");
//            Course course = ICourseDAO.findCourseByID("DN100");
//            if(course == null){
//                System.out.println("No Course with this ID");
//            }else{
//                displayCourse(course);
//                System.out.println(course);
//            }
//
//            System.out.println("\nStudentDAO Testing");
//            System.out.println("\nregisterStudent(s)");
//            Student s = new Student(71098763,"1998-11-29","12345678");
////            if(IStudentDAO.registerStudent(s)){
////                System.out.println("Successfull insert");
////            }else{
////                System.out.println("Failed");
////            }
//
//            System.out.println("\nLogin()");
//            if(IStudentDAO.login(s)){
//                System.out.println("Login Successfull");
//            }else{
//                System.out.println("Incorrect Details");
//            }
//
//            System.out.println("\nisRegistered()");
//            if(IStudentDAO.isRegistered(s)){
//                System.out.println("Is Registered");
//            }else{
//                System.out.println("Not Registered");
//            }
//
//            System.out.println("\nfindCoursesForUser(12349678)");
//            List<String> studendsCourse = ICourseChoiceDAO.getUsersCoursesChoices(12349678);
//            if(studendsCourse.isEmpty())
//            {
//                System.out.println("No choices picked yet");
//
//            }
//            else {
//                for(String sc : studendsCourse)
//                    System.out.println(sc);
//            }
//
//            System.out.println("updateCoursesForUser()");
//            List<String>studentChoices = new ArrayList<>();
//            studentChoices.add("DK845");
//            studentChoices.add("DN100");
//            studentChoices.add("DN150");
//            if(ICourseChoiceDAO.updateCoursesForUser(80910958,studentChoices))
//            {
//                System.out.println("Choices Updated");
//            }else{
//                System.out.println("Failed");
//            }
//
//
//        }
//        catch (DaoException e)
//        {
//            e.printStackTrace();
//        }
    }

    public void start()
    {
        try{
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new CAOClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
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
