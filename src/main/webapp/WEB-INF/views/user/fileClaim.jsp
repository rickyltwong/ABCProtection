<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.abcprotection.model.User"%>
<%@ page import="com.abcprotection.model.Registration"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>File Claim</title>
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
                <a class="nav-link" href="RegistrationServlet">Register Product</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ClaimServlet">File Claim<span class="sr-only">(current)</span></a>
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


<div class="container mt-5">
    <h2>File a New Claim</h2>
    <form action="ClaimServlet" method="post">
        <div class="form-group">
            <label for="registrationId">Registered Product:</label>
            <select class="form-control" id="registrationId" name="registrationId" required>
                <option value="">Please select a registration</option>
                <% 
                List<Registration> registrations = (List<Registration>) request.getAttribute("registrations");
                for (Registration reg : registrations) {
                    out.println("<option value='" + reg.getRegistrationId() + "'>" + reg.getRegistrationId() + " - " + reg.getProductName() + "</option>");
                }
                %>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit Claim</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
