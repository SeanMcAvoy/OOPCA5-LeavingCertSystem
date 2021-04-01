package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;

import java.util.List;

public interface CourseChoiceDaoInterface
{
    public List<String> getUsersCoursesChoices(int caoNumber) throws DaoException;
    public boolean updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException;
    // INSERT INTO `student_courses` (`caoNumber`, `courseID`, `preference`) VALUES ('12349678', 'DN100', '2');
}
