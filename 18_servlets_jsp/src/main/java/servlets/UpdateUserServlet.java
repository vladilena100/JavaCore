package servlets;

import dao.DaoRole;
import dao.DaoUser;
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
import java.text.ParseException;
import java.util.Map;

import static util.ValidateFields.validateFields;

@WebServlet("/users/edit")
public class UpdateUserServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(UpdateUserServlet.class);


    private final RoleService roleService = RoleService.getInstance((DaoRole) new RoleDAOFactory().getDao());

    private final UserService userService = UserService.getInstance((DaoUser) new UserDAOFactory().getDao());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = (String) req.getAttribute("id");
        long userId = Long.parseLong(id);
        req.setAttribute("action", "Edit");
        req.setAttribute("user", userService.findById(userId));
        req.setAttribute("roles", roleService.findAll());

        req.getRequestDispatcher("/view/addUpdateUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User userForUpdate = null;
        try {
            userForUpdate = RequestUtils.getUser(req);
        } catch (ParseException e) {
            LOG.error("Message: ", e);
        }
        String password = req.getParameter("password");

        Long userId = userForUpdate.getId();
        User userById = userService.findById(userId);

        Map<String, String> result = null;
        try {
            result = validateFields(req);
        } catch (ParseException e) {
            LOG.error("Message: ", e);
        }
        if (userForUpdate.getPassword() == null || userForUpdate.getPassword().isEmpty()) {
            userForUpdate.setPassword(userById.getPassword());
        } else {
            userForUpdate.setPassword(password);
        }

        if (result.isEmpty()) {
            userService.update(userForUpdate);
            User user = (User) req.getSession().getAttribute("user");
            if (userForUpdate.getId().equals(user.getId())) {
                req.getSession().setAttribute("user", userForUpdate);
            }
            resp.sendRedirect("/users");
        } else {
            req.setAttribute("error", result);
            doGet(req, resp);
        }
    }
}
