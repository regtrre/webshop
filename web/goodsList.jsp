<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品列表</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        /* 页面基本样式 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
            color: #555;
        }

        /* 商品类型按钮 */
        .category-buttons {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .category-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 5px;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .category-button:hover {
            background-color: #45a049;
        }

        .category-button.active {
            background-color: #2196F3;
        }

        /* 商品列表样式 */
        .product-list {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin-top: 30px;
        }

        .product-item {
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            width: 200px;
            margin-bottom: 20px;
            padding: 15px;
            text-align: center;
        }

        .product-item img {
            width: 100%;
            height: auto;
            border-radius: 5px;
        }

        .product-name {
            font-size: 18px;
            font-weight: bold;
            margin-top: 10px;
        }

        .product-price {
            color: #888;
            margin: 5px 0;
        }

        .product-detail-link {
            display: inline-block;
            margin-top: 10px;
            color: #2196F3;
            text-decoration: none;
        }

        .product-detail-link:hover {
            text-decoration: underline;
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

<h1>商品列表</h1>

<!-- 商品分类按钮 -->
<div class="category-buttons">
    <form action="GoodsServlet" method="get" id="categoryForm">
        <button type="submit" name="category" value="0" class="category-button <c:if test="${param.category == '0'}">active</c:if>">全部商品</button>
        <button type="submit" name="category" value="1" class="category-button <c:if test="${param.category == '1'}">active</c:if>">显卡</button>
        <button type="submit" name="category" value="2" class="category-button <c:if test="${param.category == '2'}">active</c:if>">硬盘</button>
        <button type="submit" name="category" value="3" class="category-button <c:if test="${param.category == '3'}">active</c:if>">显示器</button>
        <button type="submit" name="category" value="4" class="category-button <c:if test="${param.category == '4'}">active</c:if>">机箱</button>
        <button type="submit" name="category" value="5" class="category-button <c:if test="${param.category == '5'}">active</c:if>">输入设备</button>
    </form>
</div>

<!-- 商品列表 -->
<div class="product-list">
    <c:forEach var="goods" items="${goodsList}">
        <c:if test="${param.category == '0' || goods.typeId == param.category}">
            <div class="product-item">
                <img src="picture/${goods.cover}" alt="${goods.name}">
                <div class="product-name">${goods.name}</div>
                <div class="product-price">价格：${goods.price}</div>
                <a href="UpdateHistoryServlet?id=${goods.id}" class="product-detail-link">查看详情</a>
            </div>
        </c:if>
    </c:forEach>
</div>

</body>
</html>
