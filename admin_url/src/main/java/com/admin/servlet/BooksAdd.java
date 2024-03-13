package com.admin.servlet;
import com.entity.*;

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


@WebServlet("/addBooks")
@MultipartConfig
public class BooksAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(req.getParameter("userid"));
            System.out.println(userId);
            String bookname = req.getParameter("bname");
            String author = req.getParameter("author");
            String price = req.getParameter("price");
            String categories = req.getParameter("categories");
            String status = req.getParameter("status");
            String referenceId = req.getParameter("referenceId");
            //Part part = req.getPart("bimg");
            //String fileName = part.getSubmittedFileName();

            BookDetails b = new BookDetails(bookname, author, price, categories, status, referenceId, "admin");
            BookDAOImpl daoImpl = new BookDAOImpl(DBConnect.getConn());

            boolean f = daoImpl.addBooks(b);

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("userObj");
            int userIdFromSession = user.getId();
            System.out.println(userIdFromSession);
            if (userIdFromSession == userId) {
                if (f) {
                    //String path = getServletContext().getRealPath("") + "book";
                    //System.out.println(path);
                    //File file = new File(path);
                    //part.write(path + File.separator + fileName);

                    session.setAttribute("succMsg", "Book added successfully");
                    resp.sendRedirect("admin/add_books.jsp");
                } else {
                    session.setAttribute("failedMsg", "Something wrong on Server");
                    resp.sendRedirect("admin/add_books.jsp");
                }
            } else {
                // Invalidating session and redirecting to login page
                session.invalidate(); // Invalidate current session
                resp.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
