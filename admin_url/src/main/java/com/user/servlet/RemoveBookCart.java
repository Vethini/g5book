package com.user.servlet;

import java.io.IOException;

import com.DAO.cartDAOimpl;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/remove_book")
public class RemoveBookCart extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		int bid = Integer.parseInt(request.getParameter("bid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		cartDAOimpl cartDAOImpl = new cartDAOimpl(DBConnect.getConn());
		boolean f = cartDAOImpl.deleteBook(cid,bid,uid);
		
		HttpSession session = request.getSession();
		if(f) {
			session.setAttribute("succMsg", "Book removed from Cart successfully.");
			response.sendRedirect("checkout.jsp");
		}else {
			session.setAttribute("failedMsg", "Something went wrong on server.");
			response.sendRedirect("checkout.jsp");
		}
		
	} catch (Exception e) {
		  e.printStackTrace();
        }
	}
	
}
