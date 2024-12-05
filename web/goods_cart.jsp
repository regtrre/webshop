<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.GoodsCart"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.CartDAO"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<!-- 导航栏 -->
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


<%
 session = request.getSession();
    String username = (String) session.getAttribute("username");

    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    CartDAO cartDAO = new CartDAO();
    List<GoodsCart> cartItems = cartDAO.getCartItemsByUsername(username);
%>

<h1>购物车</h1>
<div class="table-container">
    <table>
        <tr>
            <th>商品封面</th>
            <th>商品名称</th>
            <th>单品价格</th>
            <th>数量</th>
            <th>操作</th>
        </tr>
        <%
            for (GoodsCart goodsCart : cartItems) {
        %>
        <tr>
            <td><img src="picture/<%= goodsCart.getCover() %>" alt="<%= goodsCart.getName() %>"></td>
            <td><%= goodsCart.getName() %></td>
            <td><%= goodsCart.getPrice() %></td>
            <td><%= goodsCart.getQuantity() %></td>
            <td>

                <a href="DeleteCartItemServlet?good_id=<%= goodsCart.getGoodId() %>">删除</a> <!-- 删除按钮 -->
                <a href="purchase.jsp?goods_id=<%= goodsCart.getGoodId() %>&amount=<%= goodsCart.getQuantity() %>&price=<%= goodsCart.getPrice() %>&goods_name=<%= goodsCart.getName() %>">购买</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
