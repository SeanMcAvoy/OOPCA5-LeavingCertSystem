package com.dkit.oopca5.core.johnloane;

import static org.junit.Assert.assertTrue;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.server.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public  void getAllCoursesTest()
    {
        CourseDaoInterface ICourseDAO = new MySqlCourseDAO();
        try{
            List<Course> courses = ICourseDAO.findAllCourses();
            boolean result = false;
            if(!courses.isEmpty()){
                result = true;
            }
            boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public  void findCourseByIDTest()
    {
        System.out.println("Testing with Correct ID");
        CourseDaoInterface ICourseDAO = new MySqlCourseDAO();
        try{
            Course courses = ICourseDAO.findCourseByID("DN100");
            String result = courses.getCourseId();
            String expResult = "DN100";
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public  void findCourseByIDTest2()
    {
        System.out.println("Testing with incorrect ID");
        CourseDaoInterface ICourseDAO = new MySqlCourseDAO();
        try{
            Course courses = ICourseDAO.findCourseByID("DN00");
            Boolean result = true;
            if(courses == null)
                result = false;

            Boolean expResult = false;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void registerStudentTest()
    {
        System.out.println("registerStudentTest() - has a delete method that deletes CAO Number 90988872 before it gets added to stop" +
                "primary key Dup error");
        StudentDaoInterface IStudentDAO = new MySqlStudentDAO();
        try{
            IStudentDAO.deleteStudentForTesting();
            Student s = new Student(90988872,"1998-11-29","Test");
            Boolean result = false;
            if(IStudentDAO.registerStudent(s)) {
                result = true;
            }
            Boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }

    }



    @Test
    public void loginTest()
    {
        System.out.println("Login with correct Details");
        StudentDaoInterface IStudentDAO = new MySqlStudentDAO();
        try{
            Student s = new Student(90988872,"1998-11-29","Test");
            Boolean result = false;
            if(IStudentDAO.login(s)) {
                result = true;
            }
            Boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTest2()
    {
        System.out.println("Login with wrong Details");
        StudentDaoInterface IStudentDAO = new MySqlStudentDAO();
        try{
            Student s = new Student(90988872,"1998-11-29","Tes");
            Boolean result = false;
            if(IStudentDAO.login(s)) {
                result = true;
            }
            Boolean expResult = false;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void isRegisteredTest()
    {
        StudentDaoInterface IStudentDAO = new MySqlStudentDAO();
        try{
            Student s = new Student(90988872,"1998-11-29","Test");
            Boolean result = false;
            if(IStudentDAO.isRegistered(s)) {
                result = true;
            }
            Boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void getUsersCoursesChoicesTest()
    {
        CourseChoiceDaoInterface ICourseChoiceDAO = new MySqlCourseChoice();
        try{
            Boolean result = false;
            List<String> studendsCourse = ICourseChoiceDAO.getUsersCoursesChoices(12349678);
            if(!studendsCourse.isEmpty())
            {
                result = true;
            }
            Boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void updateCoursesForUserTest()
    {
        CourseChoiceDaoInterface ICourseChoiceDAO = new MySqlCourseChoice();
        try{
            Boolean result = false;
            List<String>studentChoices = new ArrayList<>();
            studentChoices.add("DK845");
            studentChoices.add("DN100");
            studentChoices.add("DN150");
            if(ICourseChoiceDAO.updateCoursesForUser(80910958,studentChoices))
            {
                result = true; //get current choice update and see if match
            }
            Boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }


    }


}
