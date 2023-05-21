package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Cart;
import model.Product;

public class ProductDao {
	private Connection connection;
	private String query;
	private PreparedStatement stmnt;
	private ResultSet resultset;
	
	public ProductDao(Connection con) {
		this.connection = con;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		
		try {
			query = "select * from product";
			stmnt = this.connection.prepareStatement(query);
			resultset = stmnt.executeQuery();
			while(resultset.next()) {
				Product row  = new Product();
				row.setPid(resultset.getInt("product_id"));
				row.setPname(resultset.getString("product_name"));
				row.setPcategory(resultset.getString("p_category"));
				row.setPrice(resultset.getDouble("p_price"));
				row.setImg(resultset.getString("p_image"));
				
				products.add(row);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size() >0) {
				for(Cart item : cartList) {
					query = "select * from product where Product_id=?";
					stmnt = this.connection.prepareStatement(query);
					stmnt.setInt(1, item.getPid());
					resultset = stmnt.executeQuery();
					while(resultset.next()) {
						Cart row = new Cart();
						row.setPid(resultset.getInt("product_id"));
						row.setPname(resultset.getString("product_name"));
						row.setPcategory(resultset.getString("p_category"));
						row.setPrice(resultset.getDouble("p_price")*item.getQuantity());
						
						row.setQuantity(item.getQuantity());
						
						products.add(row);
					}
				}
			}
			
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		return products;
	}
	
	public double getTotatCartPrice(ArrayList<Cart> cartList) {
		double sum = 0.0;
		
		try {
			if(cartList.size()>0) {
				for(Cart item : cartList) {
					query = "select p_price from product where product_id=?";
					stmnt = this.connection.prepareStatement(query);
					stmnt.setInt(1, item.getPid());
					resultset = stmnt.executeQuery();
					
					while(resultset.next()) {
						sum = sum + resultset.getDouble("p_price")*item.getQuantity();
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return sum;
	}
	
	public Product getOneProduct(int id) {
		Product row = null;
		
		try {
			query = "select * from product where product_id=?";
			stmnt = this.connection.prepareStatement(query);
			stmnt.setInt(1, id);
			resultset = stmnt.executeQuery();
			
			while(resultset.next()) {
				row = new Product();
				row.setPid(resultset.getInt("product_id"));
				row.setPname(resultset.getString("product_name"));
				row.setPcategory(resultset.getString("p_category"));
				row.setPrice(resultset.getDouble("p_price"));
				row.setImg(resultset.getString("p_image"));
			}
		}catch(Exception e) {
			
		}
		
		return row;
	}

	public List<Product> getSearchProducts(String productname) {
		//System.out.print("product to be searched : " + productname);
		
		List<Product> s_product = new ArrayList<Product>();
		try {
			query = "Select * from ecommerce_website.product where product_name like ?";
			stmnt = this.connection.prepareStatement(query);
			stmnt.setString(1, "%" + productname + "%");
			resultset = stmnt.executeQuery();
			
			while(resultset.next()) {
				Product row = new Product();
				row.setPid(resultset.getInt("product_id"));
				row.setPname(resultset.getString("product_name"));
				row.setPcategory(resultset.getString("p_category"));
				row.setPrice(resultset.getDouble("p_price"));
				row.setImg(resultset.getString("p_image"));
				
				s_product.add(row);
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return s_product;
	}
	
}
