<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品管理</title>
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

        /* 按钮样式 */
        button {
            background-color: #1abc9c;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background-color: #16a085;
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
    <script type="text/javascript">
        function confirmDelete(id) {
            if (confirm("确定要删除该商品吗？")) {
                window.location.href = "ManageGoodsDeleteServlet?id=" + id;
            }
        }
    </script>
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

<!-- 添加商品按钮 -->
<div style="text-align: center; margin: 20px;">
    <a href="ManageGoodsAdd.jsp">
        <button>添加商品</button>
    </a>
</div>

<h1>商品列表</h1>
<table>
    <tr>
        <th>商品ID</th>
        <th>商品封面</th>
        <th>商品名称</th>
        <th>单品价格</th>
        <th>数量</th>
        <th>操作</th>
    </tr>
    <c:forEach var="goods" items="${goodsList}">
        <tr>
            <td>${goods.id}</td> <!-- 显示商品ID -->
            <td><img src="picture/${goods.cover}" alt="${goods.name}" width="100"></td>
            <td>${goods.name}</td>
            <td>${goods.price}</td>
            <td>${goods.stock}</td>
            <td>
                <a href="ManageGoodsUpdateServlet?id=${goods.id}">修改</a>
                <a href="javascript:void(0);" onclick="confirmDelete(${goods.id});">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
