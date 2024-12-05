package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ManageServlet")
public class ManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前会话
        HttpSession session = request.getSession();

        // 从会话中获取用户名
        String username = (String) session.getAttribute("username");

        // 检查用户名是否为 "123"
        if ("123".equals(username)) {
            // 用户名是管理员，转跳到管理页面
            response.sendRedirect("manage.jsp");
        } else {
            // 用户名不是管理员，提示信息并转跳到用户界面
            request.setAttribute("errorMessage", "你不是管理员，请先登录管理员账号。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
