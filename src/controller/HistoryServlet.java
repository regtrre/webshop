package controller;

import dao.HistoryDAO;
import model.History;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    private final HistoryDAO historyDAO = new HistoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户 session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println("121212");
        // 如果用户未登录，重定向到登录页面
        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 查询历史记录
        List<History> historyList = historyDAO.getHistoryByUsername(username);

        // 如果没有历史记录，初始化为空列表
        if (historyList == null) {
            historyList = new ArrayList<>();
        }

        // 将历史记录放入请求属性中
        request.setAttribute("historyList", historyList);

        // 转发到 history.jsp
        request.getRequestDispatcher("history.jsp").forward(request, response);
        response.sendRedirect("history.jsp");
    }
}
