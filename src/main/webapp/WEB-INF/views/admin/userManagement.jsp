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
			            <button type="button" class="btn btn-info edit-btn" data-toggle="modal" data-target="#editModal"
			                data-userid="${user.userId}" data-username="${user.username}" data-cellphone="${user.cellphoneNo}"
			                data-email="${user.email}" data-name="${user.name}" data-address="${user.address}">
			                Edit
			            </button>
			            <button type="button" class="btn btn-danger delete-btn" data-toggle="modal" data-target="#deleteModal"
			                data-userid="${user.userId}">
			                Delete
			            </button>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>

<!-- Delete Confirmation Modal -->
<div class="modal" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm Deletion</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this user?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <form action="${pageContext.request.contextPath}/admin/edit" method="post" style="display: inline;">
                    <input type="hidden" name="_method" value="DELETE">
                    <input type="hidden" name="userId" id="deleteFormUserId">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- Edit User Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form id="editUserForm" method="post" action="${pageContext.request.contextPath}/admin/edit">
        <div class="modal-header">
          <h5 class="modal-title" id="editModalLabel">Edit User</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <input type="hidden" name="_method" value="PUT">
          <input type="hidden" name="userId" id="editFormUserId">
          <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" required>
          </div>
          <div class="form-group">
            <label for="cellphoneNo">Phone Number:</label>
            <input type="text" class="form-control" id="cellphoneNo" name="cellphoneNo" required>
          </div>
          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
          </div>
          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
          </div>
          <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-primary">Update User</button>
        </div>
      </form>
    </div>
  </div>
</div>
<script>
$(document).ready(function() {
    $('.edit-btn').on('click', function() {
        var userId = $(this).data('userid');
        var username = $(this).data('username');
        var cellphone = $(this).data('cellphone');
        var email = $(this).data('email');
        var name = $(this).data('name');
        var address = $(this).data('address');

        $('#editFormUserId').val(userId);
        $('#username').val(username);
        $('#cellphoneNo').val(cellphone);
        $('#email').val(email);
        $('#name').val(name);
        $('#address').val(address);
        
        $('#editModal').modal('show');
    });

    $('.delete-btn').on('click', function() {
        var userId = $(this).data('userid');
        $('#deleteFormUserId').val(userId);
    });
});
</script>

</body>
</html>
