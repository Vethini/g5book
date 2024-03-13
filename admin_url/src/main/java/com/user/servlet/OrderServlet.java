package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.print.event.PrintJobListener;

import com.DAO.BookOrderImpl;
import com.DAO.cartDAOimpl;
import com.DB.DBConnect;
import com.entity.BookOrder;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			HttpSession session = request.getSession();
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			String name = request.getParameter("username");
			String email = request.getParameter("email");
			String phno = request.getParameter("phno");
			String address = request.getParameter("address");
			String landmark = request.getParameter("landmark");
			String city= request.getParameter("city");
			String state = request.getParameter("state");
			String pincode = request.getParameter("pincode");
			String paymentType = request.getParameter("payment");
			
			String fullAddress = address+", "+landmark+", "+city+", "+state+", "+pincode;
			
			cartDAOimpl cartDAOImpl  = new cartDAOimpl(DBConnect.getConn());
			List<Cart> blist = cartDAOImpl .getBookByUser(id);
//			System.out.println(name+" "+email+" "+phno+" "+ fullAddress + " "+ paymentType);
//			System.out.println(blist);
			
			if(blist.isEmpty()) {
				
				session.setAttribute("failedMsg", "No Product In Cart.");
				response.sendRedirect("checkout.jsp");
				
			}else {
				
				BookOrderImpl bookOrderImpl = new BookOrderImpl(DBConnect.getConn());
//				int i = dao2.getOrderNo();
				Random random = new Random();

				BookOrder o = null;
				
				ArrayList <BookOrder> orderList = new ArrayList<BookOrder>();
				
				for(Cart c: blist) {
					
					o = new BookOrder();
					o.setOrderId("BOOK-ORDER-00"+ random.nextInt(1000));;
					o.setUserName(name);
					o.setEmail(email);
					o.setPhno(phno);
					o.setFulladd(fullAddress);
					o.setBookname(c.getBookName());
					o.setAuthor(c.getAuthor());
					o.setPrice(c.getPrice()+"");
					o.setPaymentType(paymentType);
					orderList.add(o);
					
				}
				
				
				if("noselect".equals(paymentType)) {
					session.setAttribute("msg", "Choose Payment Method");
					response.sendRedirect("checkout.jsp");
				}else {
					Boolean isOrderSaved = bookOrderImpl.saveOrder(orderList);
					if(isOrderSaved) {
						session.setAttribute("succMsg", "Order Successfull.");
						response.sendRedirect("order_success.jsp");
					}else {
						session.setAttribute("failedMsg", "Order Failed.");
						response.sendRedirect("checkout.jsp");
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
