package com.admin.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/editBooks")
public class EditBooksServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            System.out.println("Received parameters - ID: " + req.getParameter("id") + ", Book Name: " + req.getParameter("bname") + ", Author: " + req.getParameter("author") + ", Price: " + req.getParameter("price") + ", Status: " + req.getParameter("status"));

            int id = Integer.parseInt(req.getParameter("id"));
            String bookname = req.getParameter("bname");
            String author = req.getParameter("author");
            String price= req.getParameter("price");
            String status = req.getParameter("status");
            

            BookDetails b = new BookDetails();
            b.setBookId(id);
            b.setBookName(bookname);
            b.setAuthor(author);
            b.setPrice(price);;
            b.setStatus(status);
            
            BookDAOImpl daoImpl = new BookDAOImpl(DBConnect.getConn());
            
            boolean f = daoImpl.updateEditBooks(b);
            
            HttpSession session = req.getSession();
            if(f) {
                
                session.setAttribute("succMsg", "Book Updated successfully");
            }else {
                session.setAttribute("failedMsg", "Something wrong on Server");
            }

            // Set the bookId in session for redirection
            session.setAttribute("bookId", String.valueOf(id));
            resp.sendRedirect("admin/all_books.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
