<!DOCTYPE html>
<html>
<head>
    <title>Register User</title>
    <link rel="stylesheet" type="text/css" href="css/style5.css">
</head>
<body>
<h2>Register User</h2>
<form action="AddUserServlet" method="post">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
       Email: <input type="email" name="email" required><br>
     Address: <input type="text" name="address" required><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
