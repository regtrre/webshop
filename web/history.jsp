<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.History"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>浏览历史</title>
    <link rel="stylesheet" type="text/css" href="css/style7.css">


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

<h1>浏览历史</h1>

<div class="history-list">
    <table>
        <tr>
            <th>商品名称</th>
            <th>商品封面</th>
            <th>浏览时间</th>
            <th>操作</th>
        </tr>
        <%
            // 获取历史记录列表
            List<History> historyList = (List<History>) request.getAttribute("historyList");

            // 检查历史记录是否为空
            if (historyList == null || historyList.isEmpty()) {
        %>
        <tr>
            <td colspan="4">没有浏览历史记录。</td>
        </tr>
        <%
        } else {
            // 遍历历史记录并展示
            for (History history : historyList) {
        %>
        <tr>
            <td><%= history.getGoodsName() %></td>
            <td><img src="picture/<%= history.getGoodsCover() %>" alt="<%= history.getGoodsName() %>" style="width: 100px; height: auto;"></td>
            <td><%= history.getCurrentTime() %></td>
            <td>
                <a href="goodsDetail?id=<%= history.getGoodsId() %>">查看物品</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
</body>
</html>
