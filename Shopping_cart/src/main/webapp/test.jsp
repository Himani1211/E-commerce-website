<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="LoginServlet" method="post">
    <div class="form-group">
        <label>Email Address</label>
        <input type="email" class="form-control" name="login-email" placeholder="Enter your Email" required>
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" name="login-password" placeholder="********" required>
    </div>
    <div class="text-center">
        <button type="submit" class="btn btn-success">Login</button>
    </div>
</form>

<form action="RegistrationServlet" method="post">
    <div class="text-right">
        <button type="submit" class="btn btn-warning">New User</button>
    </div>
</form>
	
</body>
</html>