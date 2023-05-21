<%@page import="connection.DBConnection"%>
<%@page import="model.*" %>
<%@page import= "dao.*"%>
<%@page import="java.util.*" %>
<%@page import="java.time.LocalDateTime" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    	
    }
    
    ProductDao pdao = new ProductDao(DBConnection.getconnection());
    String product_s = request.getParameter("search");
    List<Product> products = pdao.getSearchProducts(product_s);
    
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
    	request.setAttribute("cart_list" , cart_list);
    }
    
    %>
<!DOCTYPE html> 
<html lang="en">
<head>
<title>Welcome to shopping cart</title>
<link rel="stylesheet" href="index.css">
<%@ include file="includes/header.jsp" %>
</head> 
<body>
	<%@ include file="includes/navigation_Bar.jsp" %>
	
	<div class="container">
		
		<%-- <div class="card-header my-3"><%=displayDate() %></div>
		<div class="card-header my-3">All Products</div>--%>
		
		
	<div class="row">
		<%
			if(!products.isEmpty()){
				for(Product p : products){ %>
			
		<div class="col-md-3 my-3">
		<div class="card w-100" style="width: 18rem;">
			<img class="card-img-top  img-fluid " style="width:100%; height:200px; object-fit:cover;" src="product_images/<%= p.getImg() %>" alt="Card image cap">
			<div class="card-body">
				<h5 class="card-title"><%= p.getPname() %></h5>
				<h6 class="price">Price : <%= p.getPrice() %></h6>
				<h6 class="category">Category : <%= p.getPcategory() %></h6>
				<div class="mt-3 d-flex justify-content-between">
					<a href="AddToCartServlet?id=<%= p.getPid() %>" class="btn btn-primary">Add to Cart</a>
					<a href="OrderNowServlet?quantity=1&id=<%= p.getPid() %>" class="btn btn-success">Buy Now</a>
					
				</div>
			</div>
		</div>
	</div>
	<% }
	}
			else
				System.out.println("product list is empty");
	%>
	</div>
			
	
	
	</div>
	
	<%!
	public String displayDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		LocalDateTime now = LocalDateTime.now();
		String date = (String)formatter.format(now);
		
		return date;
	}
	
	
	%>
	</body>
</html>