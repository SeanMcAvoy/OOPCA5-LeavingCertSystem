package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Student;
//Name: Sean McAvoy
//Student Number: D00233349
public interface StudentDaoInterface
{
    /**
     * registerStudent()
     * @param  s Student
     * @return True if the Database is update / False if fails
     * @throws DaoException
     */
    public boolean registerStudent(Student s) throws DaoException;

    /**
     *Login Method
     * @param s student
     * @return true or false if student details match so can login
     * @throws DaoException
     */
    public boolean login(Student s) throws DaoException;

    /**
     * isRegistered(Student s) - check if the user is register
     * @param s student
     * @return boolean true or false depending on if register or not.
     * @throws DaoException
     */
    public boolean isRegistered(Student s) throws DaoException;

    /**
     * Deletes The Test student so every time junit Test are run the duplicate Primary Key (CAO number)
     * error is not thrown. Only deletes caoNumber 90988872
     * @throws DaoException
     */
    public void deleteStudentForTesting() throws DaoException;



}
