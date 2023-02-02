package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import connection.DBConnection;
import dao.UserDao;
import model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try (PrintWriter out = response.getWriter()){

            
            String email = request.getParameter("login-email");
    		String password = request.getParameter("login-password");
    		
    		try {
				UserDao userdao = new UserDao(DBConnection.getconnection());
				User user = userdao.userLogin(email, password);
				
				if(user == null) {
					out.println("invalid details");
				}
				else {
					request.getSession().setAttribute("auth" , user);
					response.sendRedirect("index.jsp");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
        }
        catch (FileNotFoundException e){
            System.out.print("file not found");
            e.printStackTrace();
        }
		
		
		
		
		
	}

}
