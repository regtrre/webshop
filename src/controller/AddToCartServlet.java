package controller;

import dao.CartDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private final CartDAO cartDAO = new CartDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int goodId = Integer.parseInt(request.getParameter("good_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity")); // 获取数量

        if (username != null) {
            boolean success = cartDAO.addCartItem(username, goodId, quantity);
            if (success) {
                response.sendRedirect("goods_cart.jsp"); // 添加成功后重定向到购物车页面
            } else {
                request.setAttribute("errorMessage", "添加商品到购物车失败，请重试。");
                request.getRequestDispatcher("goodsDetail.jsp?good_id=" + goodId).forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
