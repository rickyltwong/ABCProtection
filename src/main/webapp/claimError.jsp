<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Submitting Claim</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h1>Error</h1>
    <p>There was a problem submitting your claim. Please try again.</p>
    <button onclick="goBack()" class="btn btn-primary">Go Back</button>
</div>
</body>
</html>
