<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="css/style4.css">
</head>
<body>
<h2>Login</h2>
<form action="LoginServlet" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <button type="submit">Login</button>
</form>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<p style="color:red;"><%= errorMessage %></p>
<% } %>
</body>
</html>