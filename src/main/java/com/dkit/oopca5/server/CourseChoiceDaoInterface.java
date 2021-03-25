package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;

import java.util.List;

public interface CourseChoiceDaoInterface
{
    public List<String> findCoursesForUser(int caoNumber) throws DaoException;
    public void updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException;
}
