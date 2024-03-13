package com.user.servlet;

import java.io.File;
import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/add_old_Books")
// below @multipartconfig is important when we use file upload in form
@MultipartConfig
public class AddOldBook extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String bookname = request.getParameter("bname");
			String author = request.getParameter("author");
			String price= request.getParameter("price");
			String categories = "Old";
			String status = "Active";
			String referenceId = request.getParameter("referenceId");
//			Part part = req.getPart("bimg");
//			String fileName = part.getSubmittedFileName()
			
			String nameEmail = request.getParameter("user");
			
			BookDetails b = new BookDetails(bookname, author, price, categories, status, referenceId, nameEmail);
//			System.out.println(b);
			BookDAOImpl bookDAOImpl = new BookDAOImpl(DBConnect.getConn());
			
			
			boolean f = bookDAOImpl.addBooks(b);
			
			HttpSession session = request.getSession();
			if(f) {
				
//				String path = getServletContext().getRealPath("")+"book";
//				System.out.println(path);
//				File file = new File(path);
//				part.write(path + File.separator + fileName);
				
				
				session.setAttribute("succMsg", "Book added successfully");
				response.sendRedirect("sell_book.jsp");
			}else {

				session.setAttribute("failedMsg", "Something wrong on Server");
				response.sendRedirect("sell_book.jsp");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}
