package com.dkit.oopca5.server;

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler
 thread to deal with that connection. The server then returns to listening
 */

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;

import java.util.List;

public class CAOServer
{
    public static void main(String[] args)
    {
        CourseDaoInterface ICourseDAO = new MySqlCourseDAO();
        StudentDaoInterface IStudentDAO = new MySqlStudentDAO();

        try
        {
            System.out.println("getAllCourse()");
            List<Course> courses = ICourseDAO.findAllCourses();
            if(courses.isEmpty())
            {
                System.out.println("There is No Courses");
            } else{
                for(Course c : courses)
                    System.out.println(c.toString());
            }

            System.out.println("\ngetCourseByID(DN100)");
            Course course = ICourseDAO.findCourseByID("DN100");
            if(course == null){
                System.out.println("No Course with this ID");
            }else{
                System.out.println(course);
            }

            System.out.println("\nStudentDAO Testing");
            System.out.println("\nregisterStudent(s)");
            Student s = new Student(71098763,"1998-11-29","12345678");
//            if(IStudentDAO.registerStudent(s)){
//                System.out.println("Successfull insert");
//            }else{
//                System.out.println("Failed");
//            }

            System.out.println("\nLogin()");
            if(IStudentDAO.login(s)){
                System.out.println("Login Successfull");
            }else{
                System.out.println("Incorrect Details");
            }

            System.out.println("\nisRegistered()");
            if(IStudentDAO.isRegistered(s)){
                System.out.println("Is Registered");
            }else{
                System.out.println("Not Registered");
            }

        }
        catch (DaoException e)
        {
            e.printStackTrace();
        }
    }




}
