package com.admin.servlet;

import com.entity.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/delete")
public class BooksDeleteServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        HttpSession session = req.getSession();
	        User userObj = (User) session.getAttribute("userObj");
	        
	        // Check if the user is logged in and has admin privileges
	        if (userObj != null && userObj.getName().equals("Admin")) {
	            // Retrieve the book ID parameter
	            int bookId = Integer.parseInt(req.getParameter("bookId"));
	            
	            // Validate book ID - You can add additional validation logic here
	            
	            BookDAOImpl daoImpl = new BookDAOImpl(DBConnect.getConn());
	            boolean success = daoImpl.deleteteBooks(bookId);
	            
	            if (success) {
	                session.setAttribute("succMsg", "Book deleted successfully");
	            } else {
	                session.setAttribute("failedMsg", "Failed to delete book");
	            }
	        } else {
	            session.setAttribute("failedMsg", "Unauthorized access");
	        }
	        
	        // Redirect back to the all_books.jsp page
	        resp.sendRedirect("admin/all_books.jsp");
	        
	    } catch (NumberFormatException e) {
	        // Handle invalid book ID (e.g., non-numeric input)
	        HttpSession session = req.getSession();
	        session.setAttribute("failedMsg", "Invalid book ID");
	        resp.sendRedirect("admin/all_books.jsp");
	    } catch (Exception e) {
	        // Handle other exceptions
	        e.printStackTrace();
	        HttpSession session = req.getSession();
	        session.setAttribute("failedMsg", "Something went wrong on the server");
	        resp.sendRedirect("admin/all_books.jsp");
	    }
	}


}
