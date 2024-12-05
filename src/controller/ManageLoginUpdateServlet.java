package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManageLoginUpdateServlet")
public class ManageLoginUpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("ManageLoginUpdate.jsp").forward(request, response);
        } else {
            response.sendRedirect("ManageListloginServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码为 UTF-8，以支持中文输入
        request.setCharacterEncoding("UTF-8");
        // 设置响应编码为 UTF-8
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int isAdmin = Integer.parseInt(request.getParameter("isAdmin"));

        UserDAO userDAO = new UserDAO();
        User user = new User(username, null, email, address);
        user.setIsAdmin(isAdmin);

        if (userDAO.updateUser(user)) {
            request.setAttribute("message", "修改成功");
        } else {
            request.setAttribute("message", "修改失败");
        }
        response.sendRedirect("ManageListloginServlet"); // 修改成功，重定向到用户列表
    }
}
