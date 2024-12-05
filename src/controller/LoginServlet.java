package controller;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userDAO.validateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("GoodsServlet?category=0");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
