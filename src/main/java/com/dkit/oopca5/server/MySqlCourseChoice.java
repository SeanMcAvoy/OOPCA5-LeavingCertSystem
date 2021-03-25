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
    public List<String> findCoursesForUser(int caoNumber) throws DaoException
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
    public void updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException
    {
    }

}
