package dao;

import java.sql.*;
import model.Order;
import model.Product;
import model.User;
import java.util.*;
import connection.*;

public class OrderDao {
	private Connection connection;
	private String query;
	private PreparedStatement stmnt;
	private ResultSet resultset;
	
	public OrderDao(Connection con) {
		this.connection = con;
	}
	
	public boolean insertOrder(Order model) {
		boolean result = false;
		
		try {
			
			query = "insert into ecommerce_website.order (order_date, order_quantity, u_id, p_id) values(?,?,?,?)";
			stmnt = this.connection.prepareStatement(query);
			stmnt.setString(1, model.getDate());
			stmnt.setInt(2, model.getQuantity());
			stmnt.setInt(3, model.getUserId());
			stmnt.setInt(4, model.getPid());
			stmnt.executeUpdate();
			result = true;
			
			
			
			
		}catch(SQLException e) {
			 e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Order> userOrders(int id){
		List<Order> order_list = new ArrayList<>();
		
		try {
			query = "select * from ecommerce_website.order where u_id=? order by order_id desc;";
			stmnt = this.connection.prepareStatement(query);
			stmnt.setInt(1, id);
			resultset = stmnt.executeQuery();
			
			
			while(resultset.next()) {
				Order order = new Order();
				ProductDao productDao = new ProductDao(this.connection);
				int pId = resultset.getInt("p_id");
			
				
				Product product = productDao.getOneProduct(pId);
				
				order.setOrderId(resultset.getInt("order_id"));
				order.setPid(pId);
				order.setPname(product.getPname());
				order.setPcategory(product.getPcategory());
				order.setPrice(product.getPrice()*resultset.getInt("order_quantity"));
				order.setQuantity(pId);
				order.setDate(resultset.getString("order_date"));
				order_list.add(order);
				
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return order_list;
	}
	
	public void cancelOrder(int id) {
		try {
			
			query = "delete from ecommerce_website.order where order_id=?";
			stmnt = this.connection.prepareStatement(query);
			stmnt.setInt(1, id);
			stmnt.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}


	
	
	
	
	
	
