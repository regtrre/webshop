<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>添加商品</title>
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
      max-width: 600px;
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
    textarea,
    input[type="file"] {
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
    }

    input[type="submit"]:hover {
      background-color: #16a085;
    }

    textarea {
      resize: vertical;
      height: 100px;
    }

    @media (max-width: 600px) {
      form {
        padding: 15px;
      }

      input[type="submit"] {
        width: 100%;
      }
    }
  </style>
</head>
<body>

<h1>添加商品</h1>
<form action="ManageGoodsAddServlet" method="post" enctype="multipart/form-data">
  <label>商品名称:</label>
  <input type="text" name="name" required>

  <label>价格:</label>
  <input type="number" name="price" step="0.01" required>

  <label>介绍:</label>
  <textarea name="intro" required></textarea>

  <label>库存:</label>
  <input type="number" name="stock" required>

  <label>类型ID:</label>
  <input type="number" name="typeId" required>

  <label>封面:</label>
  <input type="file" name="cover" required>

  <label>图片1:</label>
  <input type="file" name="image1">

  <label>图片2:</label>
  <input type="file" name="image2">

  <input type="submit" value="添加商品">
</form>

</body>
</html>
