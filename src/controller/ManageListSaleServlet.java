package controller;

import dao.OrderDAO;
import model.SaleReport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageListSaleServlet")
public class ManageListSaleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        List<SaleReport> salesReports = orderDAO.getSalesReport();
        request.setAttribute("salesReports", salesReports);
        request.getRequestDispatcher("ManageListSale.jsp").forward(request, response);
    }
}
