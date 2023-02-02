package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.io.*;
import model.*;
import java.util.*;

import connection.DBConnection;
import dao.OrderDao;
import java.time.format.*;
import java.time.LocalDateTime;



public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public OrderNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter();){
			
			
		  
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now();
			String date = formatter.format(now);
 
			
			User auth = (User)request.getSession().getAttribute("auth");
			if(auth != null) {
				
				String productid = request.getParameter("id");
				out.print("p_id :" + productid);
				
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if(productQuantity <= 0) {
					productQuantity = 1;
					
				}
				
				Order order = new Order();
				order.setPid(Integer.parseInt(productid));
				order.setUserId(auth.getId());
				order.setQuantity(productQuantity);
				order.setDate(date);
				
				OrderDao orderDao = new OrderDao(DBConnection.getconnection());
				out.print("data is going to br inserted in database");
				boolean result = orderDao.insertOrder(order);
				out.print("result : " + result);
				if(result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if(cart_list != null) {
						for(Cart c : cart_list) {
							if(c.getPid() == Integer.parseInt(productid)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
						response.sendRedirect("cart.jsp");
					}
					else
						response.sendRedirect("orders.jsp");
					
				}else 
					out.print("order has not been placed");
				
				
				
			}
			else {
				response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
