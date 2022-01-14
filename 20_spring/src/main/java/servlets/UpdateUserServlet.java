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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping("/users/edit")
@AllArgsConstructor
public class UpdateUserServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(UpdateUserServlet.class);

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

        String id = (String) req.getAttribute("id");
        long userId = Long.parseLong(id);
        req.setAttribute("action", "Edit");
        User byId = userService.findById(userId);
        req.setAttribute("user", byId);
        req.setAttribute("selectedRoleId", byId.getRole().getId());
        req.setAttribute("user", byId);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        req.setAttribute("birthday", formatter.format(byId.getBirthday()));
        req.setAttribute("selectedRoleId", byId.getRole().getId());
        req.setAttribute("roles", roleService.findAll());

        req.getRequestDispatcher("/view/addUpdateUsers.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User userForUpdate;
        try {
            userForUpdate = requestUtils.getUser(req);
        } catch (ParseException e) {
            LOG.error("Message: ", e);
            throw new ParseException("do not parse birthday");
        }
        String password = req.getParameter("password");

        Long userId = userForUpdate.getId();
        User userById = userService.findById(userId);

        Map<String, String> result;
        try {
            result = validateFields.validateFields(req);
        } catch (ParseException | java.text.ParseException e) {
            LOG.error("Message: ", e);
            throw new ParseException("do not parse birthday");
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