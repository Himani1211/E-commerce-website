<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registration Page</title>
		
		<%@ include file="includes/header.jsp"%>
	</head>
<body>
	
	<%-- navigation bar --%>
	<%@ include file="includes/navigation_Bar.jsp" %>
	
	<%-- form --%>
    <div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Registration</div>
			<div class="card-body">
				<form action="RegistrationServlet"  method="post">
				
					<div class="form-group">
						<label>Name</label><input type="text" class="form-control" 
						name="user-name" placeholder="Enter your Name" required>
					</div>


					<div class="form-group">
						<label>Email Address</label><input type="email" class="form-control" 
						name="user-email" placeholder="Enter your Email" required>
					</div>
					
					<div class="form-group">
						<label>Contact Number</label><input type="text" class="form-control" 
						name="user-contact" placeholder="Enter Your Contact Number" required>
					</div>


					<div class="form-group">
						<label>Password</label><input type="password" class="form-control"
							name="user-password" placeholder="********" required>
					</div>


					<div class="text-center">
						<button type="submit" class="btn btn-success">SUBMIT</button>
					</div>
					

				</form>
			</div>
		</div>
	</div>
	
	
	
</body>
</html>