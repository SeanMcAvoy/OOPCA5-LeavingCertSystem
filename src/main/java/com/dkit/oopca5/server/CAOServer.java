package com.dkit.oopca5.server;

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler
 thread to deal with that connection. The server then returns to listening
 */

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;

import java.util.List;

public class CAOServer
{
    public static void main(String[] args)
    {
        CourseDaoInterface ICourseDAO = new MySqlCourseDAO();

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

        }
        catch (DaoException e)
        {
            e.printStackTrace();
        }
    }




}
