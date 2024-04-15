<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.abcprotection.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="UserDashboardServlet">ABC Protection</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="UserDashboardServlet">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="RegistrationServlet">Register Product<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ClaimServlet">File Claim</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <% User currentUser = (User) request.getSession().getAttribute("user");
               if (currentUser != null) { %>
                <li class="nav-item">
                    <span class="navbar-text">Welcome, <%=currentUser.getName()%> (<%=currentUser.getUsername()%>)</span>
                </li>
            <% } %>
        </ul>
    </div>
</nav>

<div class="container mt-3">
    <h2>Register a New Product</h2>
    <form action="RegistrationServlet" method="post">
        <div class="form-group">
            <label for="productName">Product Name:</label>
            <select class="form-control" id="productName" name="productName" required>
                <option value="">Select a product</option>
                <% 
                List<String> productNames = (List<String>) request.getAttribute("productNames");
                for (String name : productNames) {
                    out.println("<option value=\"" + name + "\">" + name + "</option>");
                }
                %>
            </select>
        </div>
        <div class="form-group">
            <label for="serialNo">Serial Number:</label>
            <input type="text" class="form-control" id="serialNo" name="serialNo" required>
        </div>
        <div class="form-group">
            <label for="purchaseDate">Purchase Date:</label>
            <input type="date" class="form-control" id="purchaseDate" name="purchaseDate" required>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
