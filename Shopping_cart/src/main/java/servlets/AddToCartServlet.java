package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddToCartServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter out = response.getWriter()){
			ArrayList<Cart> cartList = new ArrayList<>();
			
			
			int id= Integer.parseInt(request.getParameter("id"));
			Cart cart = new Cart();
			cart.setPid(id);
			cart.setQuantity(1);
			
			
			
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_listDb = (ArrayList)session.getAttribute("cart-list");
		
			if(cart_listDb == null) {
				cartList.add(cart);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
			}
			else {
				cartList = cart_listDb;
				boolean exist = false;
				
				for(Cart c : cart_listDb) {
					
					if(c.getPid() == id) {
						exist = true;
						out.println("<h3 style='color:crimson; text-align:center;'>Item is already exist in Cart. <a href='cart.jsp'>Go to Cart</a></h3>");
						
					}
					
				}
				
				if(!exist) {
					cartList.add(cart);
					response.sendRedirect("index.jsp");
					
				}
			}
			
		}
		
		
	}

	

}
