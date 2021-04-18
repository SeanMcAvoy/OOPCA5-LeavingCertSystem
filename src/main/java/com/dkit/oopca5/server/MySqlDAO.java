package com.dkit.oopca5.server;

/*
All of the database functionality should be here. You will need a DAO for each table that you are interacting with in the database
 */
import com.dkit.oopca5.Exceptions.DaoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Name: Sean McAvoy
//Student Number: D00233349

public class MySqlDAO
{
    /**
     * getConnection()
     * connects to the local server MySQL database
     * @return
     * @throws DaoException
     */
    public Connection getConnection() throws DaoException
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:8889/oop_CA5_Sean_McAvoy";
        String username = "root";
        String password = "root";
        Connection con = null;

        /** You might need use this to suit your pc as I am on mac **/

//        String driver = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/NAMEOF_database";
//        String username = "root";
//        String password = "";
//        Connection con = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException ex1)
        {
            System.out.println("Failed to find driver class " + ex1.getMessage());
            System.exit(1);
        }
        catch (SQLException ex2)
        {
            System.out.println("Connection failed " + ex2.getMessage());
            System.exit(2);
        }
        return con;
    }

    /**
     *
     * @param con Connection
     * @throws DaoException
     */
    public void freeConnection(Connection con) throws DaoException
    {
        try
        {
            if (con != null)
            {
                con.close();
                con = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
