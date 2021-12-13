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

public class ParamFromUsers {

    RoleService roleService = new RoleService(new JdbcRoleDaoImpl((ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties")))));
    UserService userService = new UserService(new JdbcUserDaoImpl((ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties")))));


    public User paramUser(HttpServletRequest req) {

        final String login = req.getParameter("Login");
        final String password = req.getParameter("Password");
        final String email = req.getParameter("Email");
        final String firstName = req.getParameter("FirstName");
        final String lastName = req.getParameter("LastName");
        final Date birthday = Date.valueOf(req.getParameter("Birthday"));
        final String role = req.getParameter("Role");

        Role roleId = roleService.findByName(role);

        return new User(login, password, email, firstName, lastName, birthday, new Role(roleId.getId(), roleId.getName()));
    }

    public User getUserByLoginPassword(String login, String password) {

        User result = new User();

        for (User user : userService.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user;
            }
        }

        return result;
    }

    public boolean userIsExist(String login, String password) {

        boolean result = false;

        for (User user : userService.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public Role getRoleByLoginPassword(String login, String password) {
        Role result = new Role();

        for (User user : userService.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }

        return result;
    }
}
