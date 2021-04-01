package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCourseChoice extends MySqlDAO implements CourseChoiceDaoInterface
{
    @Override
    public List<String> getUsersCoursesChoices(int caoNumber) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> courseIDs = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM student_courses WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, caoNumber);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                String courseId = rs.getString("courseID");
                courseIDs.add(courseId);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findCoursesForUser() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findCoursesForUser() " + e.getMessage());
            }
        }
        return courseIDs;     // may be empty
    }

    @Override
    public boolean updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean completed = false;
        int countOfRowsAffected = 0;
        int i = 0;

        try {
            con = this.getConnection();
            String deleteQuery = "DELETE FROM `student_courses` WHERE `student_courses`.`caoNumber` = ?";
            ps1 = con.prepareStatement(deleteQuery);
            ps1.setInt(1,caoNumber);
            ps1.executeUpdate();
            while(i < courses.size())
            {
                String query = "INSERT INTO `student_courses` VALUES (?,?,?)";
                ps = con.prepareStatement(query);

                ps.setInt(1, caoNumber);
                ps.setString(2, courses.get(i));
                ps.setInt(3, i+1);
                ps.executeUpdate();
                i++;
            }
            if(i == courses.size())
            {
                completed = true;
            }
        } catch (SQLException e) {
            throw new DaoException("updateCoursesForUser()" + e.getMessage());
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
                throw new DaoException("updateCoursesForUser() " + e.getMessage());
            }

        }
        return completed;
    }

}
