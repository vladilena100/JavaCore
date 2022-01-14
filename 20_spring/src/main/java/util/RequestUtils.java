package util;

import lombok.AllArgsConstructor;
import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@AllArgsConstructor
public class RequestUtils {

    private static final Logger LOG = LogManager.getLogger(RequestUtils.class);

    @Autowired
    private final RoleService roleService;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public User getUser(HttpServletRequest req) {

        final String id = req.getParameter("id");
        Long userId = null;
        if (null != id && !id.isEmpty()) {
            userId = Long.parseLong(id);
        }
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String email = req.getParameter("email");
        final String firstName = req.getParameter("firstName");
        final String lastName = req.getParameter("lastName");
        Date birthday = null;
        try {
            birthday = format.parse(req.getParameter("birthday"));
        } catch (ParseException e) {
            LOG.error("do not parse birthday: ", e);
        }
        final Long role = Long.parseLong(req.getParameter("role"));

        Role roleId = roleService.findById(role);

        return new User(userId, login, password, email, firstName, lastName, birthday, new Role(roleId.getId(), roleId.getName()));
    }
}