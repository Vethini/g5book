package com.user.servlet;

import java.io.IOException;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update_profile")
public class UpdateProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phno = request.getParameter("phno");
            String password = request.getParameter("password");
            
            UserDAOImpl daoImpl = new UserDAOImpl(DBConnect.getConn());
            HttpSession session = request.getSession();
            User us = daoImpl.getUserByEmail(email);
            
            if (daoImpl.checkEncryptedPassword(password,us)) {
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setPhno(phno);

                if (daoImpl.updateProfile(user)) {
                    session.setAttribute("succMsg", "Profile Updated Successfully.");
                } else {
                    session.setAttribute("failedMsg", "Something went wrong on the server.");
                }
            } else {
                session.setAttribute("failedMsg", "Wrong Credentials.");
            }
            response.sendRedirect("Edit_profile.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }
}
