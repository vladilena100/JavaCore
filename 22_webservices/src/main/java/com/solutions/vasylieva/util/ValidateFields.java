package com.solutions.vasylieva.util;

import com.solutions.vasylieva.dto.UserAddDTO;
import com.solutions.vasylieva.dto.UserEditDTO;
import com.solutions.vasylieva.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import com.solutions.vasylieva.services.UserService;

@Component
public class ValidateFields {

    @Autowired
    private UserService userService;

    public void validateFields(UserAddDTO user, BindingResult result) {

        User userByEmail = userService.findByEmail(user.getEmail());
        User userByLogin = userService.findByLogin(user.getLogin());
        boolean isPasswordEmpty = user.getPassword().isEmpty();
        boolean isEqualsPassPassAgain = user.getPassword().equals(user.getPasswordAgain());

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
    }

    public void validateFields(UserEditDTO user, BindingResult result) {

        final String REGEXP = "^(?=.*[0-9])(?=.*[a-z]).{4,64}$";
        User userByEmail = userService.findByEmail(user.getEmail());
        boolean isPasswordEmpty = user.getPassword().isEmpty();
        boolean isEqualsPassPassAgain = user.getPassword().equals(user.getPasswordAgain());
        Long id = userService.findByLogin(user.getLogin()).getId();

        if (userByEmail != null && !userByEmail.getId().equals(id)) {
            result.rejectValue("email", "error.user", "User vih this email is exist");
        }
        if (!isPasswordEmpty && !isEqualsPassPassAgain) {
            result.rejectValue("passwordAgain", "error.user", "Password and confirm password do not match");
        }
        if (!isPasswordEmpty && isEqualsPassPassAgain && !user.getPassword().matches(REGEXP)) {
            result.rejectValue("password", "error.user", "password must contain at least 4 characters and 1 letter and 1 number");
        }
    }
}
