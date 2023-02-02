package connection;

import java.sql.*;

public class DBConnection {
	public static Connection connection = null;
	
	public static Connection  getconnection() throws SQLException, ClassNotFoundException{
		
		if(connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ecommerce_website";
			connection = DriverManager.getConnection(url, "root","HSnjjd@1234");
			System.out.println("Connected");
		}
		
		return connection;
	}
	
	
}
