<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="javax.servlet.ServletException"%>
<%@ page import="java.io.IOException"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注销</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #333;
        }
        p {
            font-size: 18px;
            color: #555;
        }
        .redirect {
            margin-top: 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<%
    // 获取当前会话
    session = request.getSession(false);
    if (session != null) {
        // 删除用户的 session 数据
        session.invalidate(); // 使 session 失效
    }
%>
<h1>您已成功注销</h1>
<p>感谢您的使用！</p>
<p class="redirect">正在跳转到首页...</p>

<%
    // 使用 JavaScript 延迟重定向到登录页面
    response.setHeader("Refresh", "2; URL=index.jsp");
%>
</body>
</html>
