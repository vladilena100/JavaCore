package servlets;

import dao.jdbc.JdbcRoleDaoImpl;
import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import services.RoleService;
import services.UserService;
import support.ApplicationContext;
import support.ConnectionManager;
import support.DBPoolConfig;
import util.ParamFromUsersUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users/add")
public class AddUserServlet extends HttpServlet {

    private final RoleService roleService = (RoleService) ApplicationContext.getInstance().getService("userService");

    private final UserService userService = (UserService) ApplicationContext.getInstance().getService("userService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", "Add");
        req.setAttribute("request", req.getRequestURI());
        req.setAttribute("user", userService.findAll());
        req.setAttribute("roles", roleService.findAll());
        req.getRequestDispatcher("/view/addUpdateUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String loginError = "password mismatch";

        if (!req.getParameter("password").equals(req.getParameter("passwordAgain"))) {
            req.setAttribute("passwordError", loginError);
        } else {
            User user = ParamFromUsersUtil.paramUser(req);
            userService.create(user);
            resp.sendRedirect("/users");
        }

    }
}
