<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>商品详情</title>
  <link rel="stylesheet" href="css/style2.css">

  <script type="text/javascript">
    // 切换图片
    function changeImage(imgSrc) {
      document.getElementById("mainImage").src = imgSrc;
    }
  </script>

</head>

<body>
<div class="container">
  <h1>商品详情</h1>
  <!-- 商品详情内容容器 -->
  <div class="product-detail-container">
    <!-- 左侧：商品图片容器 -->
    <div class="product-images">
      <!-- 判断是否有商品详情图 -->
      <c:if test="${not empty goods.image1 or not empty goods.image2}">
        <div class="image-display">
          <img id="mainImage" src="picture/${goods.image1}" alt="商品详情图" class="main-image">
        </div>

        <!-- 图片选择器 -->
        <div class="image-selector">
          <c:if test="${not empty goods.image1}">
            <img src="picture/${goods.image1}" alt="商品图1" class="thumbnail" onclick="changeImage('picture/${goods.image1}')">
          </c:if>
          <c:if test="${not empty goods.image2}">
            <img src="picture/${goods.image2}" alt="商品图2" class="thumbnail" onclick="changeImage('picture/${goods.image2}')">
          </c:if>
        </div>
      </c:if>
    </div>

    <!-- 右侧：商品信息容器 -->
    <div class="product-info">
      <div class="product-name">${goods.name}</div>
      <div class="product-price">价格：${goods.price}</div>
      <div class="product-stock">库存：${goods.stock}</div>
      <div class="product-intro">${goods.intro}</div>
      <form action="AddToCartServlet" method="post">
        <input type="hidden" name="good_id" value="${goods.id}">
        <label for="quantity">数量：</label>
        <input type="number" name="quantity" id="quantity" value="1" min="1" required>
        <button type="submit">添加到购物车</button>
      </form>
    </div>

</div>
</body>

</html>
