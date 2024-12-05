<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.GoodsCart"%>
<%@ page import="dao.CartDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style6.css">
    <title>购买商品</title>
</head>
<body>
<%
    session = request.getSession();
    String username = (String) session.getAttribute("username");
    int goodsId = Integer.parseInt(request.getParameter("goods_id"));
    int amount = Integer.parseInt(request.getParameter("amount"));
    float price = Float.parseFloat(request.getParameter("price")); // 假设已传入商品价格
    float total = price * amount;
    String goodsName = request.getParameter("goods_name");
%>
<h1>购买商品</h1>
<form action="purchase" method="post">
    <p>商品ID: <%= goodsId %></p>
    <p>商品名称: <%= goodsName %></p>
    <p>数量: <%= amount %></p>
    <p>单价: <%= price %></p>
    <p>总金额: <%= total %></p>
    <p>电话: <input type="text" name="phone" required></p>
    <p>收货地址: <input type="text" name="address" required></p>
    <p>白嫖口令: <input type="text" name="code" required></p>
    <input type="hidden" name="goods_id" value="<%= goodsId %>">
    <input type="hidden" name="goods_name" value="<%= goodsName %>">
    <input type="hidden" name="amount" value="<%= amount %>">
    <input type="hidden" name="total" value="<%= total %>">
    <input type="submit" value="确认购买">
</form>
<% if (request.getParameter("error") != null) { %>
<p style="color:red;">口令错误，请重试。</p>
<% } %>
</body>
</html>
