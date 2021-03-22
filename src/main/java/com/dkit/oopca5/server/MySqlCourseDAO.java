package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;

import java.util.List;

public class MySqlCourseDAO extends MySqlDAO implements CourseDaoInterface
{
    @Override
    public List<Course> findAllCourse() throws DaoException
    {
        return null;
    }
}
