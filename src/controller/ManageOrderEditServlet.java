package controller;

import dao.OrderDAO;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManageOrderEditServlet")
public class ManageOrderEditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        String goodsName = request.getParameter("goodsName");
        int amount = Integer.parseInt(request.getParameter("amount"));
        float total = Float.parseFloat(request.getParameter("total"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int state = Integer.parseInt(request.getParameter("state"));

        OrderDAO orderDAO = new OrderDAO();
        boolean isUpdated = orderDAO.updateOrder(new Order(id, username, goodsId, goodsName, amount, total, phone, address, state));

        if (isUpdated) {
            request.setAttribute("successMessage", "修改成功");
        } else {
            request.setAttribute("errorMessage", "修改失败，请重试");
        }

        response.sendRedirect("ManageListOrderServlet"); // 修改成功后重定向到订单列表
    }
}
