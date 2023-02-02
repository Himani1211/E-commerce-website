package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.util.*;
import model.Cart;

public class IncrementDecrementServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	public IncrementDecrementServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

		try(PrintWriter out = response.getWriter();){
			
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));

			
		
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if(action != null && id>1) {
				if(action.equals("inc")) {
					for(Cart c : cart_list) {
						if(c.getPid() == id) {
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
				else if(action.equals("dec")) {
					for(Cart c: cart_list) {
						if(c.getPid()== id) {
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
