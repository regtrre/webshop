<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Order"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购买历史</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }

        .navbar {
            background-color: #2c3e50;
            padding: 10px;
            text-align: center;
        }

        .navbar a {
            color: white;
            padding: 14px 20px;
            text-decoration: none;
            margin: 0 10px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .navbar a:hover {
            background-color: #34495e;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 0 auto;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #1abc9c;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        @media (max-width: 600px) {
            .navbar a {
                display: block;
                margin: 5px 0;
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

<h1>购买历史</h1>

<table>
    <tr>
        <th>订单ID</th>
        <th>商品ID</th>
        <th>商品名称</th>
        <th>数量</th>
        <th>总金额</th>
        <th>电话</th>
        <th>地址</th>
        <th>状态</th>
    </tr>
    <%
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
    %>
    <tr>
        <td><%= order.getId() %></td>
        <td><%= order.getGoodsId() %></td>
        <td><%= order.getGoodsName() %></td>
        <td><%= order.getAmount() %></td>
        <td><%= order.getTotal() %></td>
        <td><%= order.getPhone() %></td>
        <td><%= order.getAddress() %></td>
        <td><%= order.getState() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="8" style="text-align: center;">没有找到购买历史记录。</td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
