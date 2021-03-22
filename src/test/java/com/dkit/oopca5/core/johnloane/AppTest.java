package com.dkit.oopca5.core.johnloane;

import static org.junit.Assert.assertTrue;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.server.CourseDaoInterface;
import com.dkit.oopca5.server.MySqlCourseDAO;
import org.junit.Assert;
import org.junit.Test;

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
    public void registerStudent()
    {

    }
}
