package util;

import dao.DaoUser;
import model.User;
import services.UserService;
import support.UserDAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class ValidateFields {

    private static final UserService userService = UserService.getInstance((DaoUser) new UserDAOFactory().getDao());

    public static Map<String, String> validateFields(HttpServletRequest req) throws ParseException {

        Map<String, String> error = new HashMap<>();

        User userByEmail = userService.findByEmail(req.getParameter("email"));
        User userByLogin = userService.findByLogin(req.getParameter("login"));

        if (req.getRequestURI().contains("add")) {
            if (userByLogin != null) {
                error.put("loginError", "User with this login is exist");
            }
            if (userByEmail != null) {
                error.put("emailError", "User vih this email is exist");
            }
        } else if (userByEmail != null && !userByEmail.getId().equals(userByEmail.getId())) { //TODO
            error.put("emailError", "User vih this email is exist");
        }

        if (!req.getParameter("password").equals(req.getParameter("passwordAgain"))) {
            error.put("passwordError", "Password and confirm password do not match");
        }

        String firstName = req.getParameter("firstName");
        if (firstName == null || firstName.isEmpty() || firstName.length() < 2) {
            error.put("firstNameError", "Enter first name");
        }

        String lastName = req.getParameter("lastName");
        if (lastName == null || lastName.isEmpty() || lastName.length() < 2) {
            error.put("lastNameError", "Enter last name");
        }

        return error;
    }
}
