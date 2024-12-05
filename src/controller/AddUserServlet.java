package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        // 检查用户名是否已存在
        if (userDAO.isUsernameTaken(username)) {
            response.getWriter().println("Username already exists. Please choose a different username.");
            return;
        }

        // 创建用户对象
        User user = new User(username, password, email, address);

        // 尝试添加用户
        if (userDAO.addUser(user)) {
            // 注册成功，返回成功信息和跳转
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>User added successfully!</h1>");
            response.getWriter().println("<p>即将为您跳转到登录界面...</p>");
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("setTimeout(function() { window.location.href = 'login.jsp'; }, 3000);");
            response.getWriter().println("</script>");
            response.getWriter().println("</body></html>");
        } else {
            response.getWriter().println("Failed to add user.");
        }
    }
}
