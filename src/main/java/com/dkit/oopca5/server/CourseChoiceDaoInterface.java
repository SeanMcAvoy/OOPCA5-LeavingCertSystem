package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;
//Name: Sean McAvoy
//Student Number: D00233349
import java.util.List;

public interface CourseChoiceDaoInterface
{
    /**
     * getUsersCoursesChoices(int caoNumber)
     * @param caoNumber users number
     * @return list of all the choices for a certain user
     * @throws DaoException
     */
    public List<String> getUsersCoursesChoices(int caoNumber) throws DaoException;

    /**
     * updateCoursesForUser()
     * @param caoNumber students number
     * @param courses list of courseID
     * @return True if the database is updated and false if fails
     * @throws DaoException
     */
    public boolean updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException;
}
