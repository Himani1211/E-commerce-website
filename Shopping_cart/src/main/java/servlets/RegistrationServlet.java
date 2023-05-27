package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import connection.DBConnection;
import dao.UserDao;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegistrationServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			PrintWriter out = response.getWriter();
			out.println("inside registration servlet class");
			
			String name = request.getParameter("user-name");
			String email = request.getParameter("user-email");
			String contact = request.getParameter("user-contact");
			String password = request.getParameter("user-password");
			
			
			
			try {
				UserDao userdao = new UserDao(DBConnection.getconnection());
				Boolean result = userdao.insertUserDetails(name, email,contact, password);
				
				
				if(result == false) {
					out.println("Details are not filled");
				}
				else {
					System.out.println("data entered correctly");
					response.sendRedirect("index.jsp");
				}
				
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				System.out.println(e);
			}
			
			
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
			
		
		
	
//		doGet(request, response);
		
	}

}
