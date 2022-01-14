package servlets;

import exception.ParseException;
import lombok.AllArgsConstructor;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.RoleService;
import services.UserService;
import util.RequestUtils;
import util.ValidateFields;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/users/add")
@AllArgsConstructor
public class AddUserServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(AddUserServlet.class);

    @Autowired
    private final RoleService roleService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final RequestUtils requestUtils;

    @Autowired
    private ValidateFields validateFields;

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
            result = validateFields.validateFields(req);
        } catch (ParseException | java.text.ParseException e) {
            LOG.error("Message: ", e);
        }

        if (!result.isEmpty()) {
            req.setAttribute("error", result);
            doGet(req, resp);
        } else {
            User user;
            try {
                user = requestUtils.getUser(req);
            } catch (ParseException e) {
                LOG.error("Message: ", e);
                throw new ParseException("failed to get user");
            }
            userService.create(user);
            resp.sendRedirect("/users");
        }
    }
}
