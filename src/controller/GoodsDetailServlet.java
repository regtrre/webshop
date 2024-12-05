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

@WebServlet("/goodsDetail")
public class GoodsDetailServlet extends HttpServlet {

    // 假设这里是根据商品 ID 获取商品详情的方法
    private Goods getGoodsById(int goodsId) {
        GoodsDAO dao = new GoodsDAO();
        return dao.getGoodsById(goodsId);  // 通过 DAO 根据 ID 获取商品详情
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取商品 ID（假设传递的是一个查询参数 id）
        String goodsIdParam = request.getParameter("id");

        if (goodsIdParam != null && !goodsIdParam.isEmpty()) {
            try {
                // 转换 ID 为整数
                int goodsId = Integer.parseInt(goodsIdParam);

                // 获取商品详情
                Goods goods = getGoodsById(goodsId);

                // 如果商品存在，将商品对象存入请求属性
                if (goods != null) {
                    request.setAttribute("goods", goods);
                } else {
                    // 如果商品不存在，可以设置为 null 或者不传递商品数据
                    request.setAttribute("goods", null);
                }

                // 转发到商品详情页面
                RequestDispatcher dispatcher = request.getRequestDispatcher("/goodsDetail.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                // 如果 ID 不是有效的数字，重定向到商品列表页面或显示错误页面
                response.sendRedirect("goodsList");  // 或者转发到错误页面
            }
        } else {
            // 如果没有提供商品 ID，重定向到商品列表页面
            response.sendRedirect("goodsList");
        }
    }
}
