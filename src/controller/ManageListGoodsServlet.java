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

@WebServlet("/ManageListGoodsServlet")
public class ManageListGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsDAO goodsDAO = new GoodsDAO();
        List<Goods> goodsList = goodsDAO.getAllGoods();
        request.setAttribute("goodsList", goodsList);
        request.getRequestDispatcher("ManageGoods.jsp").forward(request, response);
    }
}
