<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-3">
    <h2>Search User</h2>
    <form action="${pageContext.request.contextPath}/admin/reports" method="post" class="form-inline">
        <div class="form-group mb-2">
            <label for="userId" class="sr-only">User ID</label>
            <input type="text" class="form-control" id="userId" name="userId" placeholder="Enter User ID" required>
        </div>
        <button type="submit" class="btn btn-primary mb-2">Search</button>
    </form>
    
    <!-- Error Message Display -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>
</div>
<div class="container mt-3">
    <h2>User Details</h2>
    
    <c:if test="${not empty user}">
        <!-- User Information Table -->
        <h3>Personal Information</h3>
        <table class="table table-bordered table-striped">
            <tbody>
                <tr><th>ID</th></tr>
                <tr><td>${user.userId}</td></tr>

                <tr><th>Username</th></tr>
                <tr><td>${user.username}</td></tr>

                <tr><th>Phone Number</th></tr>
                <tr><td>${user.cellphoneNo}</td></tr>

                <tr><th>Email</th></tr>
                <tr><td>${user.email}</td></tr>

                <tr><th>Name</th></tr>
                <tr><td>${user.name}</td></tr>

                <tr><th>Address</th></tr>
                <tr><td>${user.address}</td></tr>
            </tbody>
        </table>

        <!-- Registered Products Table -->
        <h3>Registered Products</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Product Registration ID</th>
                    <th>Name</th>
                    <th>Serial Number</th>
                    <th>Purchase Date</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty products}">
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td>${product.registrationId}</td>
                                <td>${product.productName}</td>
                                <td>${product.serialNo}</td>
                                <td>${product.purchaseDate}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="4">No registered products.</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>

        <!-- Claims Table -->
        <h3>Claims</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Claim ID</th>
                    <th>Registration ID</th>
                    <th>Date of Claim</th>
                    <th>Description</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty claims}">
                        <c:forEach items="${claims}" var="claim">
                            <tr>
                                <td>${claim.claimId}</td>
                                <td>${claim.registrationId}</td>
                                <td>${claim.dateOfClaim}</td>
                                <td>${claim.description}</td>
                                <td>${claim.status}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="5">No claims.</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty user and empty errorMessage}">
        <p>No user found or invalid user ID.</p>
    </c:if>
</div>
</body>
</html>
