package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;

import java.util.List;
//Name: Sean McAvoy
//Student Number: D00233349
public interface CourseDaoInterface
{
    /**
     * findAllCourses()
     * @return list of all courses in MySQL database
     * @throws DaoException
     */
    public List<Course> findAllCourses() throws DaoException;

    /**
     * findCourseByID(String id)
     * @param id course ID
     * @return a course matching the ID
     * @throws DaoException
     */
    public Course findCourseByID(String id) throws DaoException;
}
