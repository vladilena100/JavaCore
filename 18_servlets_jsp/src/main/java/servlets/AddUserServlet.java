package servlets;

import dao.DaoRole;
import dao.jdbc.JdbcRoleDaoImpl;
import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import services.RoleService;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;
import support.RoleDAOFactory;
import util.ParamFromUsersUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static util.ParamFromUsersUtil.validateFields;


@WebServlet("/users/add")
public class AddUserServlet extends HttpServlet {

    private final RoleService roleService = RoleService.getInstance((DaoRole) new RoleDAOFactory().getDao());

    private final UserService userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties"))));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", "Add");
        req.setAttribute("roles", roleService.findAll());

        Date bookingDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String maxDate = sdf.format(bookingDate);
        req.setAttribute("maxDate", maxDate);

        req.getRequestDispatcher("/view/addUpdateUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> result = validateFields(req);

        if (!result.isEmpty()) {
            req.setAttribute("error", result);
            doGet(req, resp);
        } else {
            User user = ParamFromUsersUtil.paramUser(req);
            userService.create(user);
            resp.sendRedirect("/users");
        }
    }
}
