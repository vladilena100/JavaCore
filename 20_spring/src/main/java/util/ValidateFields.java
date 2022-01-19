package util;

import dto.UserAddDTO;
import dto.UserEditDTO;
import dto.UserRegisterDTO;
import jakarta.validation.Valid;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import services.UserService;

import javax.servlet.http.HttpServletRequest;

@Component
public class ValidateFields {

    @Autowired
    private UserService userService;

    final String REGEXP = "^(?=.*[0-9])(?=.*[a-z]).{4,64}$";

    public BindingResult validateFields(@Valid UserAddDTO user, BindingResult result, HttpServletRequest req) {

        User userByEmail = userService.findByEmail(user.getEmail());
        User userByLogin = userService.findByLogin(user.getLogin());
        boolean isPasswordEmpty = user.getPassword().isEmpty();
        boolean isEqualsPassPassAgain = user.getPassword().equals(user.getPasswordAgain());


        if (req.getRequestURI().contains("add")) {
            if (userByLogin != null) {
                result.rejectValue("login", "error.user", "User with this login is exist");
            }
            if (userByEmail != null) {
                result.rejectValue("email", "error.user", "User vih this email is exist");
            }

            if (isPasswordEmpty) {
                result.rejectValue("password", "error.user", "password can not be empty");
            }

            if (!isEqualsPassPassAgain) {
                result.rejectValue("passwordAgain", "error.user", "Password and confirm password are different");
            }

            if (isEqualsPassPassAgain && !req.getParameter("password").matches(REGEXP)) {
                result.rejectValue("password", "error.user", "password must contain at least 4 characters and 1 letter and 1 number");
            }
        }
        return result;
    }

    public BindingResult validateFields(@Valid UserEditDTO user, BindingResult result, HttpServletRequest req) {

        User userByEmail = userService.findByEmail(user.getEmail());
        boolean isPasswordEmpty = user.getPassword().isEmpty();
        boolean isEqualsPassPassAgain = user.getPassword().equals(user.getPasswordAgain());

        if (req.getRequestURI().contains("edit")) {

            if (userByEmail != null && !userByEmail.getId().equals(Long.valueOf(req.getParameter("id")))) {
                result.rejectValue("email", "User vih this email is exist");
            }
            if (!isPasswordEmpty && !isEqualsPassPassAgain) {
                result.rejectValue("passwordAgain", "Password and confirm password do not match");
            }
            if (!isPasswordEmpty && isEqualsPassPassAgain && !req.getParameter("password").matches(REGEXP)) {
                result.rejectValue("password", "password must contain at least 4 characters and 1 letter and 1 number");
            }
        }
        return result;
    }

    public BindingResult validateFields(@Valid UserRegisterDTO user, BindingResult result, HttpServletRequest req) {

        User userByEmail = userService.findByEmail(user.getEmail());
        User userByLogin = userService.findByLogin(user.getLogin());
        boolean isPasswordEmpty = user.getPassword().isEmpty();
        boolean isEqualsPassPassAgain = user.getPassword().equals(user.getPasswordAgain());


        if (req.getRequestURI().contains("add") || req.getRequestURI().contains("registration")) {
            if (userByLogin != null) {
                result.rejectValue("login", "error.user", "User with this login is exist");
            }
            if (userByEmail != null) {
                result.rejectValue("email", "error.user", "User vih this email is exist");
            }

            if (isPasswordEmpty) {
                result.rejectValue("password", "error.user", "password can not be empty");
            }

            if (!isEqualsPassPassAgain) {
                result.rejectValue("passwordAgain", "error.user", "Password and confirm password are different");
            }

            if (isEqualsPassPassAgain && !req.getParameter("password").matches(REGEXP)) {
                result.rejectValue("password", "password must contain at least 4 characters and 1 letter and 1 number");
            }
        }
        return result;
    }

}
