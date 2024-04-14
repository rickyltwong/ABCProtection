<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up Form</title>
</head>
<body>
<h2>User Registration</h2>
<form action="<%=request.getContextPath()%>/signup" method="post">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
    Confirm Password: <input type="password" name="confirm_password" required><br>
    Cellphone: <input type="text" name="cellphone_no"><br>
    Email: <input type="email" name="email" required><br>
    Name: <input type="text" name="name"><br>
    Address: <input type="text" name="address"><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
