package servlets;

import dao.jdbc.JdbcUserDaoImpl;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;
import util.ParamFromUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/add")
public class AddUserServlet extends HttpServlet {

    private final UserService userService;

    public AddUserServlet() {
        this.userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties"))));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", "Add");
        req.setAttribute("request", req.getRequestURI());
        req.setAttribute("user", userService.findAll());
        req.getRequestDispatcher("/view/addUpdateUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String loginError = "password mismatch";

        if (!req.getParameter("password").equals(req.getParameter("passwordAgain"))) {
            req.setAttribute("passwordError", loginError);
        } else {
            userService.create(ParamFromUsers.paramUser(req));
            doGet(req, resp);
        }

    }
}
