<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-3">
    <h2>Product Management</h2>
    
    <form action="${pageContext.request.contextPath}/admin/dashboard?section=products" method="post" class="form-inline">
	    <div class="form-group mb-2">
	        <input type="text" name="searchProduct" class="form-control" placeholder="Search product by name">
	    </div>
	    <button type="submit" class="btn btn-primary mb-2">Search</button>
	</form>
	<!-- add a product -->
    <form action="${pageContext.request.contextPath}/admin/products" method="POST" class="form-row align-items-center">
        <div class="col-auto">
            <input type="text" class="form-control mb-2" id="addProductName" name="addProductName" placeholder="Product Name" required>
        </div>
        <div class="col-auto">
            <input type="text" class="form-control mb-2" id="addModel" name="addModel" placeholder="Model" required>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary mb-2">Add Product</button>
        </div>
    </form>
    <table class="table table-bordered">
        <thead>
            <tr>
            	<th>Product ID</th>
                <th>Product Name</th>
                <th>Model</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                	<td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.model}</td>
                    <td>
                        <button type="button" class="btn btn-info edit-btn" data-toggle="modal" data-target="#editModal" data-productid="${product.productId}" data-productname="${product.productName}" data-model="${product.model}">Edit</button>
                        <button type="button" class="btn btn-danger delete-btn" data-toggle="modal" data-target="#deleteModal" data-productid="${product.productId}">Delete</button>
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
                <p>Are you sure you want to delete this product?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" method="post" data-dismiss="modal">Cancel</button>
                <form action="${pageContext.request.contextPath}/admin/products" method="post">
                    <input type="hidden" name="_method" value="DELETE">
                    <input type="hidden" name="productId" id="deleteFormProductId">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form id="editProductForm" method="post" action="${pageContext.request.contextPath}/admin/products">
        <div class="modal-header">
          <h5 class="modal-title" id="editModalLabel">Edit Product</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <input type="hidden" name="_method" value="PUT">
          <input type="hidden" name="productId" id="editFormProductId">
          <div class="form-group">
            <label for="editProductName">Product Name:</label>
            <input type="text" class="form-control" id="editProductName" name="productName" required>
          </div>
          <div class="form-group">
            <label for="editModel">Model:</label>
            <input type="text" class="form-control" id="editModel" name="model" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-primary">Update Product</button>
        </div>
      </form>
    </div>
  </div>
</div>
<script>

$(document).ready(function() {
	/* update a product */
    $('.edit-btn').on('click', function() {
        var productId = $(this).data('productid');
        console.log('Product ID:', productId); 
        var productName = $(this).data('productname');
        var model = $(this).data('model');  

        $('#editFormProductId').val(productId);
        $('#editProductName').val(productName);
        $('#editModel').val(model);
        
        /* $('#editModal').modal('show'); */
    });
	/* delete a product */
    $('.delete-btn').on('click', function() {
        var productId = $(this).data('productid');
        console.log('Product ID:', productId); 
        $('#deleteFormProductId').val(productId);
        
    });
    /* add a product */
    $('#addProductForm').submit(function(event) {
        var productName = $('#addProductName').val().trim();
        var model = $('#addModel').val().trim();

        if (!productName || !model) {
            alert('Both Product Name and Model are required.');
            event.preventDefault();  // Prevent form from submitting if validation fails
            return false;
        }
    });
});
</script>


</body>
</html>

