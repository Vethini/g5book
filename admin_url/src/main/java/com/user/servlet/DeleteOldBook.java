package com.user.servlet;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete_old_book")
public class DeleteOldBook extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String emString = request.getParameter("em");
			int id = Integer.parseInt(request.getParameter("id"));
			BookDAOImpl bookDAOImpl = new BookDAOImpl(DBConnect.getConn());
			
			boolean f = bookDAOImpl.oldBookDelete(emString, "Old", id);
			
			HttpSession session = request.getSession();
			if(f) {
				session.setAttribute("succMsg", "Old Book removed successfully.");
				response.sendRedirect("old_book.jsp");
			}else {
				session.setAttribute("failedMsg", "Something went wrong on server.");
				response.sendRedirect("old_book.jsp");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
