package com.dkit.oopca5.Exceptions;
import java.sql.SQLException;
//Name: Sean McAvoy
//Student Number: D00233349
public class DaoException extends SQLException
{
    public DaoException()
    {

    }
    public DaoException(String aMessage)
    {
        super(aMessage);
    }
}
