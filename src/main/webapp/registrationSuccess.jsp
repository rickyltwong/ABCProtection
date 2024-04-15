<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
    <!-- Optional: include Bootstrap if you need it here -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script>
        // JavaScript to show an alert and then redirect
        window.onload = function() {
            // Show an alert box
            alert("Registration successful!");

            // Redirect to the dashboard page
            window.location.href = 'UserDashboardServlet'; // Use the correct URL pattern for your dashboard servlet
        };
    </script>
</head>
<body>
    <!-- Optional content here, such as a message or image indicating success -->
    <div class="container mt-5">
        <h1>Registration Successful!</h1>
        <p>Your product registration was successful. You will be redirected to the dashboard shortly.</p>
    </div>
</body>
</html>
