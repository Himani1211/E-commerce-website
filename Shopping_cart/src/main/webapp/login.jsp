<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.*" %>
<%@page import="java.util.*" %>
	<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    	response.sendRedirect("index.jsp");
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
    	request.setAttribute("cart_list" , cart_list);
    }
    
    
    %>
<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
	
	<%@ include file="includes/header.jsp"%>
	
</head>
<body>

	<%-- navigation bar --%>
	<%@ include file="includes/navigation_Bar.jsp" %>
	
	<%-- form --%>
    <div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="LoginServlet"  method="post">


					<div class="form-group">
						<label>Email Address</label><input type="email"class="form-control" name="login-email" 
						placeholder="Enter your Email" required>
					</div>


					<div class="form-group">
						<label>Password</label><input type="password" class="form-control"
							name="login-password" placeholder="********" required>
					</div>


					<div class="text-center">
						<button type="submit" class="btn btn-success">Login</button>
					</div>

				</form>
			</div>
		</div>
	</div>



	
	<%-- footer --%>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>