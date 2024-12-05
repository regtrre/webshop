package controller;

import dao.OrderDAO;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageViewPurchaseHistoryServlet")
public class ManageViewPurchaseHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username"); // 从请求中获取用户名
        if (username == null) {
            username = (String) session.getAttribute("username"); // 如果没有提供用户名，从会话中获取
        }

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getOrdersByUsername(username); // 获取用户的订单列表

        request.setAttribute("orders", orders); // 将订单列表设置为请求属性
        request.getRequestDispatcher("ManageViewPurchaseHistory.jsp").forward(request, response); // 转发到 JSP 页面
    }
}
