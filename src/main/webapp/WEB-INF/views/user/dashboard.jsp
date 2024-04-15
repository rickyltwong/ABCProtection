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
    <style>
        .approved { background-color: #28a745; } /* green */
        .rejected { background-color: #dc3545; } /* red */
        .pending { background-color: #ffc107; }  /* yellow */
    </style>
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
    <h2>Registered Products and Claims</h2>
    <table class="table table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>Product Name</th>
                <th>Serial No</th>
                <th>Purchase Date</th>
                <th>Claims</th>
            </tr>
        </thead>
        <tbody>
            <% List<Registration> productsList = (List<Registration>) request.getAttribute("productsList");
               if (productsList != null) {
                   for (Registration reg : productsList) {
                       out.println("<tr><td>" + reg.getProductName() + "</td><td>" + reg.getSerialNo() + 
                           "</td><td>" + reg.getPurchaseDate() + "</td>");
                       out.println("<td>");
                       out.println("<table class='table'>");
                       out.println("<tr><th>Claim ID</th><th>Description</th><th>Status</th></tr>");
                       List<Claim> claimsList = reg.getClaims();
                       if (claimsList != null && !claimsList.isEmpty()) {
                           for (Claim claim : claimsList) {
                               String rowClass = "pending";
                               if ("approved".equalsIgnoreCase(claim.getStatus())) {
                                   rowClass = "approved";
                               } else if ("rejected".equalsIgnoreCase(claim.getStatus())) {
                                   rowClass = "rejected";
                               }
                               out.println("<tr class='" + rowClass + "'><td>" + claim.getClaimId() + "</td><td>" + claim.getDescription() +
                                   "</td><td>" + claim.getStatus() + "</td></tr>");
                           }
                       } else {
                           out.println("<tr><td colspan='3'>No claims filed</td></tr>");
                       }
                       out.println("</table>");
                       out.println("</td></tr>");
                   }
               }
            %>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>