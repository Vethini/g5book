package com.user.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verifyOTP")
public class VerifyOTPServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        
        try {
            int otp = Integer.parseInt(request.getParameter("otp"));
            Integer storedOTP = (Integer) session.getAttribute("otp");

            if (otp == storedOTP) {
                // OTP is correct, allow the user to reset the password
                request.setAttribute("email", session.getAttribute("email"));
                dispatcher = request.getRequestDispatcher("changePassword.jsp");
            } else {
                // OTP is incorrect, redirect back to the enter OTP page
                request.setAttribute("error", "Invalid OTP, please try again.");
                dispatcher = request.getRequestDispatcher("enterOTP.jsp");
            }
        } catch (NumberFormatException e) {
            // Invalid OTP format, redirect back to the enter OTP page
            request.setAttribute("error", "Invalid OTP format, please enter a valid OTP.");
            dispatcher = request.getRequestDispatcher("enterOTP.jsp");
        }
        
        dispatcher.forward(request, response);
    }
}
