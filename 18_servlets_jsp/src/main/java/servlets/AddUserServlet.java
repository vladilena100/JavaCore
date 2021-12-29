package servlets;

import dao.DaoRole;
import dao.DaoUser;
import exception.ParseException;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.RoleService;
import services.UserService;
import support.RoleDAOFactory;
import support.UserDAOFactory;
import util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.ValidateFields.validateFields;

@WebServlet("/users/add")
public class AddUserServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(AddUserServlet.class);

    private final RoleService roleService = RoleService.getInstance((DaoRole) new RoleDAOFactory().getDao());

    private final UserService userService = UserService.getInstance((DaoUser) new UserDAOFactory().getDao());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", "Add");
        req.setAttribute("roles", roleService.findAll());
        req.getRequestDispatcher("/view/addUpdateUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> result = null;
        try {
            result = validateFields(req);
        } catch (ParseException | java.text.ParseException e) {
            LOG.error("Message: ", e);
        }

        if (!result.isEmpty()) {
            req.setAttribute("error", result);
            doGet(req, resp);
        } else {
            User user = null;
            try {
                user = RequestUtils.getUser(req);
            } catch (ParseException e) {
                LOG.error("Message: ", e);
                throw new ParseException("do not parse birthday");
            }
            userService.create(user);
            resp.sendRedirect("/users");
        }
    }
}
