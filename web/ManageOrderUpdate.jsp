<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="model.Order" %>
<%@ page import="dao.OrderDAO" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="java.io.IOException" %>
<%@ page import="javax.servlet.annotation.WebServlet" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改订单</title>
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
            max-width: 500px;
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

        input[type="text"],
        input[type="number"],
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

        @media (max-width: 600px) {
            form {
                padding: 15px;
            }
        }
    </style>
</head>
<body>

<%
    // 获取订单 ID
    String orderId = request.getParameter("id");
    OrderDAO orderDAO = new OrderDAO();
    Order order = orderDAO.getOrderById(Integer.parseInt(orderId)); // 根据 ID 获取订单信息
%>

<h1>修改订单</h1>

<form action="ManageOrderEditServlet" method="post">
    <input type="hidden" name="id" value="<%= order.getId() %>"/>

    <label for="username">用户名:</label>
    <input type="text" id="username" name="username" value="<%= order.getUsername() %>" required/>

    <label for="goodsId">商品ID:</label>
    <input type="number" id="goodsId" name="goodsId" value="<%= order.getGoodsId() %>" required/>

    <label for="goodsName">商品名称:</label>
    <input type="text" id="goodsName" name="goodsName" value="<%= order.getGoodsName() %>" required/>

    <label for="amount">数量:</label>
    <input type="number" id="amount" name="amount" value="<%= order.getAmount() %>" required/>

    <label for="total">总金额:</label>
    <input type="text" id="total" name="total" value="<%= order.getTotal() %>" required/>

    <label for="phone">电话:</label>
    <input type="text" id="phone" name="phone" value="<%= order.getPhone() %>" required/>

    <label for="address">地址:</label>
    <input type="text" id="address" name="address" value="<%= order.getAddress() %>" required/>

    <label for="state">状态:</label>
    <select id="state" name="state" required>
        <option value="1" <c:if test="${order.state == 1}">selected</c:if>>待发货</option>
        <option value="2" <c:if test="${order.state == 2}">selected</c:if>>配送中</option>
        <option value="3" <c:if test="${order.state == 3}">selected</c:if>>已送达</option>
    </select>

    <input type="submit" value="提交修改"/>
</form>

</body>
</html>
