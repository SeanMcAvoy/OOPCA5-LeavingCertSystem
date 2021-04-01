package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Student;

public interface StudentDaoInterface
{
    public boolean registerStudent(Student s) throws DaoException;
    public boolean login(Student s) throws DaoException;
    public boolean isRegistered(Student s) throws DaoException;


}
