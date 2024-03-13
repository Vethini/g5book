package com.user.servlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import com.DAO.*;
import com.entity.*;
import com.DB.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String name = request.getParameter("fname");
			String email = request.getParameter("email");
			String phno = request.getParameter("phno");
			String password = request.getParameter("password");
			String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			String check= request.getParameter("check");
			
//			System.out.print(name+" "+email+" "+phno+" "+password+" "+check);
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPhno(phno);
			user.setPassword(encryptedPassword);
//			System.out.print(us);
			
			HttpSession session = request.getSession();
			
			if(check != null) {
			
				UserDAOImpl userDAO = new UserDAOImpl(DBConnect.getConn());
				
				if(userDAO.checkUser(email)) {
					
					boolean f = userDAO.userRegister(user);
					
					if(f) {
//						System.out.println("U"
//								+ "ser register successfully");
						session.setAttribute("succMsg", "Registration Successful.");
						response.sendRedirect("index.jsp");
					}else {
//						System.out.println("Something went wrong.");
						session.setAttribute("failedMsg", "Somesthing went wrong on server..");
						response.sendRedirect("registration.jsp");					
					}
					
				}else {
					
					session.setAttribute("failedMsg", "User already exist.");
					response.sendRedirect("registration.jsp");
					
				}
				
			
			}else {
//				System.out.println("Please Check Agree & Terms Condition");
				session.setAttribute("failedMsg", "Please Check Agree Terms & Condition");
				response.sendRedirect("registration.jsp");		
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
