package util;

import dao.jdbc.JdbcRoleDaoImpl;
import model.Role;
import model.User;
import services.RoleService;
import support.ConnectionManager;
import support.DBPoolConfig;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;


public class ParamFromUsersUtil {

    private static final RoleService roleService = new RoleService(new JdbcRoleDaoImpl((ConnectionManager
            .getInstance(new DBPoolConfig("jdbc.properties")))));


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

    public static int getAgeFromDateOfBirthday(Date birthday) {

        java.sql.Date myDate = new java.sql.Date( (new java.util.Date()).getTime());

        return (myDate.getYear()) - birthday.getYear();
    }
}
