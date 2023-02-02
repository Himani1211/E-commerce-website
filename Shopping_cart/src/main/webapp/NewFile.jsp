<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
	<div class = "container">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="" method="post">


					<div class="form-group">
						<label>Email Address</label><input type="email"
							class="form-control" name="login-email"
							placeholder="Enter your Email" required>
					</div>


					<div class="form-group">
						<label>Password</label><input type="password" class="form-control"
							name="login-password" placeholder="********" required>
					</div>


					<div class="text-center">
						<button type="submit" class="btn btn-primarty">Login</button>
					</div>

				</form>
			</div>
	
	</div>
	



	<%@ include file="includes/footer.jsp"%>
</body>
</html>