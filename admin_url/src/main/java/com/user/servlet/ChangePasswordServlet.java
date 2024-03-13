package com.user.servlet;

import java.io.IOException;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String newPassword = request.getParameter("password");
	    String email = (String) session.getAttribute("email");
	    if (newPassword == null) {
	        // Handle case where email is not found in session
	        response.sendRedirect("login.jsp"); // Redirect to login page
	        return;
	    }

	   
//	    System.out.println(email);
//	    System.out.println(newPassword);

	    UserDAOImpl userDAOImpl = new UserDAOImpl(DBConnect.getConn());
	    try {
	        boolean f = userDAOImpl.changePassword(email, newPassword);
	        if (f) {
	            session.setAttribute("succMsg", "Password Updated Successfully.");
	            response.sendRedirect("login.jsp");
	        } else {
	            session.setAttribute("failedMsg", "Failed to update password.");
	            response.sendRedirect("changePassword.jsp");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception
	    }
	}
}
