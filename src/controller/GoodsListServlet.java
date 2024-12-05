package controller;

import dao.GoodsDAO;
import model.Goods;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/goodsList")
public class GoodsListServlet extends HttpServlet {

    // 假设这里是从数据库获取商品列表的方法
    private List<Goods> getGoodsList() {
        GoodsDAO dao = new GoodsDAO();
        return dao.getAllGoods();  // 通过 DAO 从数据库中获取商品列表
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取商品列表
        List<Goods> goodsList = getGoodsList();

        // 将商品列表放入请求属性
        request.setAttribute("goodsList", goodsList);

        // 转发请求到 goodsList.jsp 页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/goodsList.jsp");
        dispatcher.forward(request, response);
    }
}
