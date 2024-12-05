package controller;

import dao.GoodsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManageGoodsDeleteServlet")
public class ManageGoodsDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GoodsDAO goodsDAO = new GoodsDAO();
        goodsDAO.deleteGoods(id); // 删除商品
        response.sendRedirect("ManageListGoodsServlet"); // 重定向到商品管理页面
    }
}
