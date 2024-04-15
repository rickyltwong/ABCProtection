<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Claims</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-3">
    <h2>Recent Claims</h2>
    
    <!-- Search Form -->
    <form action="${pageContext.request.contextPath}/admin/dashboard?section=claims" method="post" class="form-inline mb-3">
        <div class="form-group">
            <label for="status" class="mr-sm-2">Status:</label>
            <select class="form-control mr-sm-2" id="status" name="status">
                <option value="">All</option> 
                <option value="Pending">Pending</option>
                <option value="Approved">Approved</option>
                <option value="Rejected">Rejected</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    <c:if test="${not empty errorMessage}">
	    <div class="alert alert-danger" role="alert">
	        ${errorMessage}
	    </div>
	</c:if>

    <!-- Claims Table -->
    <table class="table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Product Registration ID</th>
            <th>Description</th>
            <th>Create Date</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${claims}" var="claim">
            <tr>
                <td>${claim.claimId}</td>
                <td>${claim.registrationId}</td>
                <td>${claim.dateOfClaim}</td>
                <td>${claim.description}</td>
                <td>${claim.status}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/claims" method="POST">
                        <input type="hidden" name="claimId" value="${claim.claimId}" />
                        <!-- <input type="hidden" name="_method" value="PUT"> -->
                        <input type="hidden" name="status" id="status${claim.claimId}" />
                        <button type="button" class="btn btn-success" onclick="confirmStatusChange(this.form, 'Approved', ${claim.claimId});">Approve</button>
                        <button type="button" class="btn btn-danger" onclick="confirmStatusChange(this.form, 'Rejected', ${claim.claimId});">Reject</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
<script>
function confirmStatusChange(form, status, claimId) {
    var message = 'Are you sure you want to ' + (status === 'Approved' ? 'approve' : 'reject') + ' this claim?';
    if (confirm(message)) {
        document.getElementById('status' + claimId).value = status;
        form.submit();
    }
}
</script>
</body>
</html>
