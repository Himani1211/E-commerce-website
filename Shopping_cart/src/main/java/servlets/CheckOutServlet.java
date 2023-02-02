package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.Order;
import model.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connection.DBConnection;
import dao.OrderDao;

import java.io.*;

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckOutServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now();
			String date = formatter.format(now);
			
			// retrieve all the product 
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			// user authentication
			User auth = (User)request.getSession().getAttribute("auth");
			//check authentication and cart list
			if(cart_list != null && auth != null) {
				
				for(Cart c: cart_list) {
					//prepare the order object
					Order order = new Order();
					order.setPid(c.getPid());
					order.setUserId(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(date);
					
					//instantiate the dao class
					OrderDao orderDao = new OrderDao(DBConnection.getconnection());
					// calling
					boolean result = orderDao.insertOrder(order);
					if(!result) break;
					}
				cart_list.clear();
				response.sendRedirect("orders.jsp");
				
			}
			else {
				if(auth == null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
