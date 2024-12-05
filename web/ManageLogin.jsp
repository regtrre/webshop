<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户管理</title>
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

        /* 表格样式 */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #2c3e50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e0e0e0;
        }

        /* 返回主页按钮样式 */
        .back-link {
            display: block;
            text-align: center;
            margin: 20px 0;
            color: #2c3e50;
            text-decoration: none;
            font-size: 16px;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        /* 响应式设计 */
        @media (max-width: 600px) {
            .navbar a {
                float: none;
                width: 100%;
                text-align: left;
            }

            table {
                font-size: 14px;
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

<h1>用户管理</h1>

<table>
    <tr>
        <th>用户名</th>
        <th>邮箱</th>
        <th>地址</th>
        <th>是否管理员</th>
        <th>操作</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.address}</td>
            <td>${user.isAdmin == 1 ? '是' : '否'}</td>
            <td>
                <a href="ManageLoginUpdateServlet?username=${user.username}">修改</a>
                <a href="ManageViewHistoryServlet?username=${user.username}">查看浏览历史</a>
                <a href="ManageViewPurchaseHistoryServlet?username=${user.username}">查看购买历史</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="index.jsp" class="back-link">返回主页</a>

</body>
</html>
