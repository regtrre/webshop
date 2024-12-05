<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改用户信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 20px;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="email"],
        input[type="text"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #1abc9c;
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #16a085;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        @media (max-width: 600px) {
            form {
                padding: 15px;
            }
        }
    </style>
</head>
<body>

<h1>修改用户信息</h1>

<form action="ManageLoginUpdateServlet" method="post">
    <input type="hidden" name="username" value="${user.username}"/>

    <label for="email">邮箱:</label>
    <input type="email" id="email" name="email" value="${user.email}" required/>

    <label for="address">地址:</label>
    <input type="text" id="address" name="address" value="${user.address}" required/>

    <label for="isAdmin">是否管理员:</label>
    <select id="isAdmin" name="isAdmin">
        <option value="0" <c:if test="${user.isAdmin == 0}">selected</c:if>>否</option>
        <option value="1" <c:if test="${user.isAdmin == 1}">selected</c:if>>是</option>
    </select>

    <input type="submit" value="提交修改"/>
</form>

<a href="ManageListloginServlet">返回用户管理</a>

</body>
</html>
