package controller;

import dao.CartDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DeleteCartItemServlet")
public class DeleteCartItemServlet extends HttpServlet {
    private final CartDAO cartDAO = new CartDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int goodId = Integer.parseInt(request.getParameter("good_id"));

        if (username != null) {
            boolean success = cartDAO.deleteCartItem(username, goodId);
            if (success) {
                response.sendRedirect("goods_cart.jsp"); // 删除成功后重定向到购物车页面
            } else {
                request.setAttribute("errorMessage", "删除商品失败，请重试。");
                request.getRequestDispatcher("goods_cart.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
