<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        body { padding-top: 20px; }
        .hidden { display: none; }
    </style>
    <script>
    $(document).ready(function() {
        $('.nav-link').click(function(e) {
            e.preventDefault();  // Prevent default anchor click behavior
            var targetSection = $(this).attr('href');  // Get the href attribute
            $('.content-section').addClass('hidden');  // Hide all sections
            $(targetSection).removeClass('hidden');  // Show the targeted section
        });
    });
    </script>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="display-4">Admin Dashboard</h1>
        <p class="lead">Welcome to the ABC Company Admin Panel. Use the navigation below to manage the platform.</p>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/admin/dashboard?section=users" class="list-group-item list-group-item-action">Manage Users</a>
				<a href="${pageContext.request.contextPath}/admin/dashboard?section=products" class="list-group-item list-group-item-action">Manage Products</a>
				<a href="${pageContext.request.contextPath}/admin/dashboard?section=claims" class="list-group-item list-group-item-action">Manage Claims</a>
				<a href="${pageContext.request.contextPath}/admin/dashboard?section=reports" class="list-group-item list-group-item-action">Generate Reports</a>

            </div>
        </div>

        <div class="col-md-9">
	    	<c:choose>
	        <c:when test="${section == 'users'}">
	            <%@ include file="userManagement.jsp" %>
	        </c:when>
	    	</c:choose>
		</div>
    </div>
</div>
</body>
</html>


