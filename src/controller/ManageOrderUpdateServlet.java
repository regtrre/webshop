package controller;

import dao.OrderDAO;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManageOrderUpdateServlet")
public class ManageOrderUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getOrderById(orderId); // 获取单个订单信息
        request.setAttribute("order", order);
        request.getRequestDispatcher("ManageOrderUpdate.jsp").forward(request, response);
    }
}
