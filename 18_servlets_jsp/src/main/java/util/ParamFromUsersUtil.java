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

    private static final RoleService roleService = new RoleService(new JdbcRoleDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties"))));

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

    public static Map<String, String> validateFields(HttpServletRequest req, String method) {

        Map<String, String> error = new HashMap<>();

        User userForUpdate = ParamFromUsersUtil.paramUser(req);
        Long userId = userForUpdate.getId();
        User userEmail = userService.findByEmail(userForUpdate.getEmail());

        if (method.equals("add")) {
            if (userService.findByLogin(req.getParameter("login")) != null) {
                error.put("loginError", "User with this login is exist");
            }
        }

        if (!req.getParameter("password").equals(req.getParameter("passwordAgain"))) {
            error.put("passwordError", "Password and confirm password do not match");
        }

        if (method.equals("add")) {
            if (userService.findByEmail(req.getParameter("email")) != null) {
                error.put("emailError", "User vih this email is exist");
            }
        } else if (userEmail != null && !userId.equals(userEmail.getId())) {
            error.put("emailError", "User vih this email is exist");
        }


        if (req.getParameter("firstName") == null || req.getParameter("firstName").isEmpty()) {
            error.put("firstNameError", "Enter first name");
        }

        if (req.getParameter("lastName") == null || req.getParameter("lastName").isEmpty()) {
            error.put("lastNameError", "Enter last name");
        }

        if (req.getParameter("birthday") == null) {
            error.put("birthdayError", "Enter birthday");
        }

        return error;
    }
}
