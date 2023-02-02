package dao;
import java.sql.*;
import model.User;
public class UserDao {
	private Connection connection;
	private String query;
	private PreparedStatement stmnt;
	private ResultSet resultset;
	
	public UserDao(Connection con) {
		this.connection = con;
	}
	
	
	public User userLogin(String email, String password) {
		User user = null;
		
		
		try {
			query = "select * from users where users_email=? and users_password=?";
			stmnt = connection.prepareStatement(query);
			stmnt.setString(1,email);
			stmnt.setString(2, password);
			resultset = stmnt.executeQuery();
			
			
			if(resultset.next()){
				user = new User();
				user.setId(resultset.getInt("users_id"));
				user.setName(resultset.getString("users_name"));
				user.setEmail(resultset.getString("users_email"));
				//user.setPassword(resultset.getString("users_password"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
	}
}
