package controller;

import dao.GoodsDAO;
import model.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {

    // 处理GET请求，展示商品列表
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsDAO goodsDAO = new GoodsDAO();

        // 获取请求中的商品分类参数（默认为0，表示全部商品）
        String category = request.getParameter("category");
        if (category == null || category.isEmpty()) {
            category = "0"; // 默认显示全部商品
        }

        // 获取商品列表
        List<Goods> goodsList;
        if ("0".equals(category)) {
            goodsList = goodsDAO.getAllGoods(); // 获取所有商品
        } else {
            int typeId = Integer.parseInt(category);
            goodsList = goodsDAO.getGoodsByTypeId(typeId); // 根据typeId获取商品
        }

        // 将商品列表和分类信息传递给JSP
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("category", category);

        // 转发到JSP页面
        request.getRequestDispatcher("/goodsList.jsp").forward(request, response);
    }
}
