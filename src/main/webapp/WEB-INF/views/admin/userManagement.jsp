<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>User Management</h2>
    <form action="${pageContext.request.contextPath}/admin/dashboard?section=users" method="post" class="form-inline">
    <div class="form-group mb-2">
        <input type="text" name="searchQuery" class="form-control" placeholder="Search user by ID">
    </div>
    <button type="submit" class="btn btn-primary mb-2">Search</button>
</form>
    
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>Name</th>
                <th>Address</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${usersList}" var="user">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.username}</td>
                    <td>${user.cellphoneNo}</td>
                    <td>${user.email}</td>
                    <td>${user.name}</td>
                    <td>${user.address}</td>
                    <td>
                        <a href="editUser?userId=${user.userId}" class="btn btn-info">Edit</a>
                        <a href="deleteUser?userId=${user.userId}" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">
        ${errorMessage}
    </div>
</c:if>
</div>
</body>
</html>
