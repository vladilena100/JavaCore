package servlets;

import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(LoginServlet.class);

    private final UserService userService;

    public LoginServlet() {
        this.userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties"))));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.getUserByLoginPassword(login, password);
        LOG.info("user from db {}", user);
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/users");
    }
}
