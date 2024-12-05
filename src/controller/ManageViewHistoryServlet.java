package controller;

import dao.HistoryDAO;
import model.History;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageViewHistoryServlet")
public class ManageViewHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        HistoryDAO historyDAO = new HistoryDAO();
        List<History> historyList = historyDAO.getHistoryByUsername(username);

        // 将历史记录放入请求属性中
        request.setAttribute("historyList", historyList);

        // 转发到 ManageViewHistory.jsp
        request.getRequestDispatcher("ManageViewHistory.jsp").forward(request, response);
    }
}
