<%@page import="connection.DBConnection"%>
<%@page import="dao.*" %>
<%@page import="model.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
List<Order> orders = null;
if (auth != null) 
	request.setAttribute("auth", auth);
else
	response.sendRedirect("login.jsp");


ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list != null){
	request.setAttribute("cart_list" , cart_list);
	orders =(List<Order>) new OrderDao(DBConnection.getconnection()).userOrders(auth.getId());
	
}
else{
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html>
<head>
<title>Order Page</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
	<%@ include file="includes/navigation_Bar.jsp"%>
	<div class="container">
	
	<table class="table table-light table-striped">
	<thead>
		<tr>
			<th scope="col">DATE</th>
			<th scope="col">NAME</th>
			<th scope="col">CATEGORY</th>
			<th scope="col">QUANTITY</th>
			<th scope="col">PRICE</th>
			<th scope="col">CANCEL</th>
		</tr>
		
	</thead>
	<tbody>
		<%
			if(orders != null){
				for(Order o:orders){%>
					<tr>
					<td><%= o.getDate() %></td>
					<td><%= o.getPname() %></td>
					<td><%= o.getPcategory() %></td>
					<td><%= o.getQuantity() %></td>
					<td><%= o.getPrice() %></td>
					<td><a class= "btn btn-sm btn-outline-danger" href="CancelOrderServlet?id=<%= o.getPid() %>">Cancel</tr>
				<% }
			}
		
		%>
	
	</tbody>
	</table>
	</div>	
	<%@ include file="includes/footer.jsp"%>
	
</body>
</html>