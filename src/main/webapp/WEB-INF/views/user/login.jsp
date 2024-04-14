<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h2>User Login</h2>
<% String notification = (String) request.getAttribute("NOTIFICATION");
   if(notification != null){
       out.println("<p>" + notification + "</p>");
   }
%>
<form action="login" method="post">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
