package com.dhruv.servlet1;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class StudentServlet extends HttpServlet {

    private static final String DBURL =
            "jdbc:mysql://localhost:3306/Servlet";
    private static final String DBUSERNAME = "root";
    private static final String DBPASSWORD = "1234";

   

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
    
    	
    	PrintWriter out = response.getWriter();
    	
    	
        String sName = request.getParameter("sName");
        String sEmail = request.getParameter("sEmail");
        String sCity = request.getParameter("sCity");
       
        out.print(sName);
        out.print(sEmail);
        out.print(sCity);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    DBURL, DBUSERNAME, DBPASSWORD);

            PreparedStatement ps =
                    connection.prepareStatement( "INSERT INTO Student (sName, sEmail, sCity) VALUES (?, ?, ?)");

            ps.setString(1, sName);
            ps.setString(2, sEmail);
            ps.setString(3, sCity);

            int rowsAffected = ps.executeUpdate();

            ps.close();
            connection.close();

            if (rowsAffected > 0) {
                response.setContentType("text/html");
                response.getWriter().println(
                        "<h2>Student registered successfully!</h2>");
                response.getWriter().println(
                        "<a href='index.html'>Register another student</a>");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
        
        
        
    }
}
