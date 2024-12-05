package controller;

import dao.OrderDAO;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageListOrderServlet")
public class ManageListOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("ManageOrder.jsp").forward(request, response);

    }
}
