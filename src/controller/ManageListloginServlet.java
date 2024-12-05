package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageListloginServlet")
public class ManageListloginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("ManageLogin.jsp").forward(request, response);
    }
}
