package util;

import dao.jdbc.JdbcRoleDaoImpl;
import dao.jdbc.JdbcUserDaoImpl;
import model.Role;
import model.User;
import services.RoleService;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;


public class ParamFromUsersUtil {

    private static final RoleService roleService = RoleService.getInstance(new JdbcRoleDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties"))));

    private static final UserService userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties"))));


    private ParamFromUsersUtil() {
    }

    public static User paramUser(HttpServletRequest req) {

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
        final Date birthday = Date.valueOf(req.getParameter("birthday"));
        final String role = req.getParameter("role");

        Role roleId = roleService.findByName(role);

        return new User(userId, login, password, email, firstName, lastName, birthday, new Role(roleId.getId(), roleId.getName()));
    }

    public static Map<String, String> validateFields(HttpServletRequest req) {

        Map<String, String> error = new HashMap<>();

        User userForUpdate = ParamFromUsersUtil.paramUser(req);
        Long userId = userForUpdate.getId();
        User userEmail = userService.findByEmail(userForUpdate.getEmail());
        User userByLogin = userService.findByLogin(userForUpdate.getLogin());

        if (req.getRequestURI().contains("add")) {
            if (userByLogin != null) {
                error.put("loginError", "User with this login is exist");
            }
            if (userEmail != null) {
                error.put("emailError", "User vih this email is exist");
            }
        } else if (userId != null && userEmail != null && !userEmail.getId().equals(userId)) {
            error.put("emailError", "User vih this email is exist");
        }

        if (!userForUpdate.getPassword().equals(req.getParameter("passwordAgain"))) {
            error.put("passwordError", "Password and confirm password do not match");
        }

        String firstName = userForUpdate.getFirstName();
        if (firstName == null || firstName.isEmpty() || firstName.length() < 2) {
            error.put("firstNameError", "Enter first name");
        }

        String lastName = userForUpdate.getLastName();
        if (lastName == null || lastName.isEmpty()) {
            error.put("lastNameError", "Enter last name");
        }

        return error;
    }
}
