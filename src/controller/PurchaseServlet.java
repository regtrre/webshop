package controller;

import dao.CartDAO;
import dao.OrderDAO;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置请求编码为 UTF-8
        request.setCharacterEncoding("UTF-8");
        // 设置响应编码为 UTF-8
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        int goodsId = Integer.parseInt(request.getParameter("goods_id"));
        String goodsName = request.getParameter("goods_name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        float total = Float.parseFloat(request.getParameter("total"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String code = request.getParameter("code");

        // 验证口令
        if ("1234".equals(code)) {
            OrderDAO orderDAO = new OrderDAO();
            int orderId = orderDAO.getNextOrderId();
            Order order = new Order(orderId, username, goodsId, goodsName, amount, total, phone, address, 1);
            System.out.println("Username: " + username);
            System.out.println("Goods ID: " + goodsId);
            System.out.println("Goods : " + goodsName);
            System.out.println("Amount: " + amount);
            System.out.println("Total: " + total);
            System.out.println("Phone: " + phone);
            System.out.println("Address: " + address);
            if (orderDAO.addOrder(order)) {
                // 删除购物车中的商品
                CartDAO cartDAO = new CartDAO();
                cartDAO.deleteCartItem(username, goodsId);
                response.sendRedirect("success.jsp"); // 成功页面
            } else {
                response.sendRedirect("error.jsp"); // 错误页面
            }
        } else {
            response.sendRedirect("purchase.jsp?error=wrong_code"); // 口令错误
        }
    }
}
