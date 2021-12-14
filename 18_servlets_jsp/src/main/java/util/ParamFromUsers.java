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

    private static RoleService roleService = new RoleService(new JdbcRoleDaoImpl((ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties")))));


    private ParamFromUsers() {
    }

    public static User paramUser(HttpServletRequest req) {

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String email = req.getParameter("email");
        final String firstName = req.getParameter("firstName");
        final String lastName = req.getParameter("lastName");
        final Date birthday = Date.valueOf(req.getParameter("birthday"));
        final String role = req.getParameter("role");

        Role roleId = roleService.findByName(role);

        return new User(login, password, email, firstName, lastName, birthday, new Role(roleId.getId(), roleId.getName()));
    }
}
