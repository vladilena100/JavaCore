package servlets;

import dao.jdbc.JdbcRoleDaoImpl;
import dao.jdbc.JdbcUserDaoImpl;
import exception.FoundUserException;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.RoleService;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;
import util.ParamFromUsersUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;


@WebServlet("/users/add")
public class AddUserServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(AddUserServlet.class);

    private final RoleService roleService = new RoleService(new JdbcRoleDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties"))));

    private final UserService userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties"))));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", "Add");
        req.setAttribute("request", req.getRequestURI());
        req.setAttribute("roles", roleService.findAll());

        Date bookingDate = new Date(new java.util.Date().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String maxDate = sdf.format(bookingDate);
        req.setAttribute("maxDate", maxDate);

        req.getRequestDispatcher("/view/addUpdateUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.getParameter("password").equals(req.getParameter("passwordAgain"))) {
            LOG.error("Password and confirm password doesn't match");
            throw new FoundUserException("Password and confirm password doesn't match");
        } else {
            User user = ParamFromUsersUtil.paramUser(req);
            userService.create(user);
            resp.sendRedirect("/users");
        }
    }
}
