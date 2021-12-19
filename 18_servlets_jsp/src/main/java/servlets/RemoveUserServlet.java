package servlets;

import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import services.UserService;
import support.ApplicationContext;
import support.ConnectionManager;
import support.DBPoolConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users/delete")
public class RemoveUserServlet extends HttpServlet {

    private final UserService userService = (UserService) ApplicationContext.getInstance().getService("userService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = (String) req.getAttribute("id");
        long userId = Long.parseLong(id);
        User userById = userService.findById(userId);
        userService.remove(userById);
        resp.sendRedirect("/users");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}
}
