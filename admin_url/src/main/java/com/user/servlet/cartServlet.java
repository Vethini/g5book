package com.user.servlet;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DAO.cartDAOimpl;
import com.DB.DBConnect;
import com.entity.BookDetails;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class cartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Check if user is logged in
        if (session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login page
            return;
        }

        try {
            int userIdFromSession = (Integer) session.getAttribute("userId");
            int uid = Integer.parseInt(request.getParameter("uid")); // Get user ID from request

            // Check if the current user matches the user ID sent from the HTML form
            if (userIdFromSession != uid) {
                response.sendRedirect("login.jsp"); // Redirect to login page
                return;
            }

            int bid = Integer.parseInt(request.getParameter("bid")); // Get book ID from request

            BookDAOImpl bookDAOImpl = new BookDAOImpl(DBConnect.getConn());
            BookDetails b = bookDAOImpl.getBookById(bid);

            if (b == null) {
                // Handle invalid book ID
                response.sendRedirect("error.jsp"); // Redirect to error page
                return;
            }

            Cart cart = new Cart();
            cart.setBid(bid);
            cart.setUserId(uid);
            cart.setBookName(b.getBookName());
            cart.setAuthor(b.getAuthor());
            cart.setPrice(Double.parseDouble(b.getPrice()));
            cart.setTotalPrice(Double.parseDouble(b.getPrice()));

            cartDAOimpl cartDAOimpl = new cartDAOimpl(DBConnect.getConn());
            boolean f = cartDAOimpl.addcart(cart);

            if (f) {
                session.setAttribute("addCart", "Book added to cart");
                response.sendRedirect("all_new_book.jsp");
            } else {
                session.setAttribute("failed", "Something went wrong!");
                response.sendRedirect("all_new_book.jsp");
            }

        } catch (NumberFormatException e) {
            // Handle invalid user ID or book ID
            response.sendRedirect("error.jsp"); // Redirect to error page
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to error page
        }
    }
}
