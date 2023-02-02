<%@page import="connection.DBConnection"%>
<%@page import="model.User" %>
<%@page import="model.Cart" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import ="dao.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    	
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if(cart_list != null){
    	ProductDao pdao = new ProductDao(DBConnection.getconnection());
    	cartProduct = pdao.getCartProducts(cart_list);
    	double total = pdao.getTotatCartPrice(cart_list);
    	request.setAttribute("cart_list" , cart_list);
    	request.setAttribute("total" , total);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Cart Page</title>
<%@ include file="includes/header.jsp" %>
<style type="text/css">
.table tbody td{
	vertical-align:middle;
	
}
.btn-incre, .btn-decre{
	box-shadow:none;
	font-size:25px;
	}
</style>
</head> 
<body>
<%@ include file="includes/navigation_Bar.jsp" %>
<div class="container">
	<div class="d-flex py-3">
		<h3>Total Price : ${(total>0)?total:0} /-</h3><a class='mx-3 btn btn-warning' href="CheckOutServlet">Place Order</a>
	</div>
	<table class="table table-light table-striped">
	<thead>
		<tr>
			<th scope="col">NAME</th>
			<th scope="col">CATEGORY</th>
			<th scope="col">PRICE</th>
			<th scope="col">BUY NOW</th>
			<th scope="col">CANCEL</th>
		</tr>
		
	</thead>
	<tbody>

	<%
	if(cart_list != null){
		
		for(Cart c: cartProduct){ %>	
			<tr>
			<td><%= c.getPname() %></td>
			<td><%= c.getPcategory() %></td>
			<td><%= c.getPrice() %></td>
			<td>
				<form action="OrderNowServlet" method="post" class="form-inline">
					<input type="hidden" name="p_id" value="<%= c.getPid() %>" class="form-input">
					<div class="form-group d-flex justify-content-between">
						<a class="btn btn-sm btn-decre" href="IncrementDecrementServlet?action=dec&id=<%= c.getPid()%>"><i class="fas fa-minus-square"></i></a>
						<input type="text" name="quantity" class="form-control" value="<%= c.getQuantity() %>" > 
						<a class="btn btn-sm btn-incre" href="IncrementDecrementServlet?action=inc&id=<%= c.getPid()%>"><i class="fas fa-plus-square"></i></a>
					</div>	
					<button type="submit" class="btn btn-success btn-sm">BUY</button>
				</form>
			</td>
			<td><a class="btn btn-sm btn-danger" href="RemoveFromCartServlet?id=<%= c.getPid() %>">Remove</a></td>
		</tr>
		<% }
	}	
		%>
	
		
	</tbody>
	</table>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>