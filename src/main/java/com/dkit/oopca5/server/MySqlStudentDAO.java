package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//Name: Sean McAvoy
//Student Number: D00233349
public class MySqlStudentDAO extends MySqlDAO implements StudentDaoInterface
{
    @Override
    public boolean registerStudent(Student s) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean completed = false;
        int countOfRowsAffected = 0;

        try {
            con = this.getConnection();

            String query = "INSERT INTO `student` VALUES (?,?,?)";
            ps = con.prepareStatement(query);

            ps.setInt(1, s.getCaoNumber());
            ps.setString(2, s.getDayOfBirth());
            ps.setString(3, s.getPassword());

            completed = (ps.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("registerStudent() " + e.getMessage());
        } finally {
            try {
                if (countOfRowsAffected > 0) {
                    completed = true;
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("registerStudent() " + e.getMessage());
            }

        }
        return completed;
    }

    @Override
    public boolean login(Student s) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean login = false;
        Student studentLogin = null;

        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM student WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, s.getCaoNumber());

            rs = ps.executeQuery();
            if(rs.next())
            {
                int caoNumber = rs.getInt("caoNumber");
                String DOB = rs.getString("dob");
                String password = rs.getString("password");
                studentLogin = new Student(caoNumber,DOB,password);
            }
        } catch (SQLException e) {
            throw new DaoException("login() " + e.getMessage());
        } finally {
            try {
                if(studentLogin != null)
                {
                    if (studentLogin.getCaoNumber() == s.getCaoNumber() && studentLogin.getDayOfBirth().equalsIgnoreCase(s.getDayOfBirth()) && studentLogin.getPassword().equals(s.getPassword()))
                    {
                        login = true;
                    }
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("login() " + e.getMessage());
            }

        }
        return login;
    }

    @Override
    public boolean isRegistered(Student s) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isRegistered = false;
        Student studentLogin = null;

        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM student WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, s.getCaoNumber());

            rs = ps.executeQuery();
            if(rs.next())
            {
                int caoNumber = rs.getInt("caoNumber");
                String DOB = rs.getString("dob");
                String password = rs.getString("password");
                studentLogin = new Student(caoNumber,DOB,password);
            }
        } catch (SQLException e) {
            throw new DaoException("isRegistered() " + e.getMessage());
        } finally {
            try {
                if(studentLogin != null)
                {
                    if (studentLogin.getCaoNumber() == s.getCaoNumber())
                    {
                        isRegistered = true;
                    }
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("isRegistered() " + e.getMessage());
            }

        }
        return isRegistered;
    }

    @Override
    public void deleteStudentForTesting() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = this.getConnection();

            String query = "DELETE FROM `student` WHERE `student`.`caoNumber` = 90988872";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("deleteStudent() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("deleteStudent() " + e.getMessage());
            }

        }
    }

}
