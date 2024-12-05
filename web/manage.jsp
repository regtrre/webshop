<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员界面</title>
    <style>
        /* 设置整体的页面字体和背景颜色 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* 导航栏样式 */
        .navbar {
            background-color: #2c3e50;
            overflow: hidden;
            padding: 10px 0;
            text-align: center;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            padding: 14px 20px;
            font-size: 16px;
            display: inline-block;
            margin: 0 10px;
        }

        .navbar a:hover {
            background-color: #1abc9c;
        }

        /* 标题样式 */
        h1 {
            text-align: center;
            color: #2c3e50;
            margin: 20px 0;
        }

        /* 响应式设计 */
        @media (max-width: 600px) {
            .navbar a {
                float: none;
                width: 100%;
                text-align: left;
            }
        }
    </style>
</head>
<body>

<!-- 导航栏 -->
<div class="navbar">
    <a href="index.jsp">主页</a>
    <a href="ManageListGoodsServlet">商品管理</a>
    <a href="ManageListOrderServlet">订单管理</a>
    <a href="ManageListSaleServlet">销售统计报表</a>
    <a href="ManageListloginServlet">用户管理</a>
</div>


</body>
</html>
