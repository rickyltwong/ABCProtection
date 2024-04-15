<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.abcprotection.model.Registration"%>
<%@ page import="com.abcprotection.model.Claim"%>
<%@ page import="com.abcprotection.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
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
                <a class="nav-link" href="UserDashboardServlet">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="RegistrationServlet">Register Product</a>
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

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h2>Registered Products</h2>
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Registration ID</th>
                        <th>Product Name</th>
                        <th>Serial No</th>
                        <th>Purchase Date</th>
                    </tr>
                </thead>
                <tbody>
                    <% List<Registration> productsList = (List<Registration>) request.getAttribute("productsList");
                       if (productsList != null) {
                           for (Registration reg : productsList) {
                               out.println("<tr><td>" + reg.getRegistrationId() + "</td><td>" + reg.getProductName() + "</td><td>" + reg.getSerialNo() + 
                                   "</td><td>" + reg.getPurchaseDate() + "</td></tr>");
                           }
                       }
                    %>
                </tbody>
            </table>
        </div>
        <div class="col-md-6">
            <h2>Filed Claims</h2>
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Claim ID</th>
                        <th>Registration ID</th>
                        <th>Date of Claim</th>
                        <th>Description</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% List<Claim> claimsList = (List<Claim>) request.getAttribute("claimsList");
                       if (claimsList != null) {
                           for (Claim claim : claimsList) {
                               out.println("<tr><td>" + claim.getClaimId() + "</td><td>" + claim.getRegistrationId() + 
                                   "</td><td>" + claim.getDateOfClaim() + "</td><td>" + claim.getDescription() + 
                                   "</td><td>" + claim.getStatus() + "</td></tr>");
                           }
                       }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
