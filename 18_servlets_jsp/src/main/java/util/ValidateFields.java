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

        final String REGEXP = "^(?=.*[0-9])(?=.*[a-z]).{4,64}$";

        User userByEmail = userService.findByEmail(req.getParameter("email"));
        User userByLogin = userService.findByLogin(req.getParameter("login"));
        boolean isPasswordEmpty = req.getParameter("password").isEmpty();
        boolean isEqualsPassPassAgain = req.getParameter("password").equals(req.getParameter("passwordAgain"));

        if (req.getRequestURI().contains("add")) {
            if (userByLogin != null) {
                error.put("loginError", "User with this login is exist");
            }
            if (userByEmail != null) {
                error.put("emailError", "User vih this email is exist");
            }
            if (!isEqualsPassPassAgain) {
                error.put("passwordError", "Password and confirm password do not match");
            }
            if (!isPasswordEmpty && isEqualsPassPassAgain
                    && !req.getParameter("password").matches(REGEXP)) {
                error.put("passwordLengthError", "password must contain at least 4 characters and 1 letter and 1 number");
            }
        }

        if (req.getRequestURI().contains("edit")) {

            if (userByEmail != null && !userByEmail.getId().equals(Long.valueOf(req.getParameter("id")))) {
                error.put("emailError", "User vih this email is exist");
            }
            if (!isPasswordEmpty && !isEqualsPassPassAgain) {
                error.put("passwordError", "Password and confirm password do not match");
            }
            if (!isPasswordEmpty && isEqualsPassPassAgain && !req.getParameter("password").matches(REGEXP)) {
                error.put("passwordLengthError", "password must contain at least 4 characters and 1 letter and 1 number");
            }
        }

        String firstName = req.getParameter("firstName");
        if (firstName == null || firstName.length() < 2) {
            error.put("firstNameError", "Enter first name");
        }

        String lastName = req.getParameter("lastName");
        if (lastName == null || lastName.length() < 2) {
            error.put("lastNameError", "Enter last name");
        }

        return error;
    }
}
