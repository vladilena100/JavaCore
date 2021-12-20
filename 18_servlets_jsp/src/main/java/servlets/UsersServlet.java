package servlets;

import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/users", loadOnStartup = 1)
public class UsersServlet extends HttpServlet {

    private final UserService userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties"))));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User user = (User) req.getSession().getAttribute("user");
        String role = user.getRole().getName();
        if (role.equals("ADMIN")) {
            req.setAttribute("users", userService.findAll());
            req.getRequestDispatcher("/view/adminPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/view/userPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
