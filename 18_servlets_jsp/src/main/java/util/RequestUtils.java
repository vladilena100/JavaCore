package util;

import dao.DaoRole;
import model.Role;
import model.User;
import services.RoleService;
import support.RoleDAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RequestUtils {

    private static final RoleService roleService = RoleService.getInstance((DaoRole) new RoleDAOFactory().getDao());
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");


    private RequestUtils() {
    }

    public static User getUser(HttpServletRequest req) {

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
        Date birthday= null;
        try {
            birthday = format.parse(req.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String role = req.getParameter("role");

        Role roleId = roleService.findByName(role);

        return new User(userId, login, password, email, firstName, lastName, birthday, new Role(roleId.getId(), roleId.getName()));
    }
}
