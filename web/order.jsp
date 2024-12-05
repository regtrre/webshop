<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Order"%>
<%@ page import="dao.OrderDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>订单信息</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .navbar {
            background-color: #333;
            overflow: hidden;
        }
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
        .navbar a.active {
            background-color: #4CAF50;
            color: white;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .no-orders {
            text-align: center;
            font-size: 18px;
            color: #888;
        }
    </style>
</head>
<body>
<!-- 导航栏 -->
<div class="navbar">
    <a href="register.jsp" class="active">注册</a>
    <a href="login.jsp">登录</a>
    <a href="index.jsp">主页</a>
    <a href="goods_cart.jsp">购物车</a>
    <a href="order.jsp">订单</a>
    <a href="HistoryServlet">历史</a>
    <a href="ManageServlet">管理</a>
    <a href="logout.jsp">注销</a>
</div>
<h1>我的订单</h1>
<%
    session = request.getSession();
    String username = (String) session.getAttribute("username");

    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    OrderDAO orderDAO = new OrderDAO();
    List<Order> orders = orderDAO.getOrdersByUsername(username);

    if (orders.isEmpty()) {
%>
<p class="no-orders">您还没有订单。</p>
<%
} else {
%>
<table>
    <tr>
        <th>商品名称</th>
        <th>数量</th>
        <th>价格</th>
        <th>收货地址</th>
        <th>手机号码</th>
        <th>状态</th>
    </tr>
    <%
        for (Order order : orders) {
            String state;
            switch (order.getState()) {
                case 1: state = "待发货"; break;
                case 2: state = "配送中"; break;
                case 3: state = "已送达"; break;
                default: state = "未知状态"; break;
            }
    %>
    <tr>
        <td><%= order.getGoodsName() %></td>
        <td><%= order.getAmount() %></td>
        <td><%= order.getTotal() %></td>
        <td><%= order.getAddress() %></td>
        <td><%= order.getPhone() %></td>
        <td><%= state %></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
</body>
</html>
