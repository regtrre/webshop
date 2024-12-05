<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>浏览历史</title>
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

        img {
            border-radius: 4px;
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

<h1>浏览历史</h1>

<table>
    <tr>
        <th>商品名称</th>
        <th>商品封面</th>
        <th>浏览时间</th>
    </tr>
    <c:forEach var="history" items="${historyList}">
        <tr>
            <td>${history.goodsName}</td>
            <td>
                <img src="picture/${history.goodsCover}" alt="${history.goodsName}" width="100" height="100"/>
            </td>
            <td>${history.currentTime}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
