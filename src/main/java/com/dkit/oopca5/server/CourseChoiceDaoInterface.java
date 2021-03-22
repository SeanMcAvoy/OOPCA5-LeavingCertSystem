package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;

import java.util.List;

public interface CourseChoiceDaoInterface
{
    public List<Course> displayCurrentChoices() throws DaoException;
}
