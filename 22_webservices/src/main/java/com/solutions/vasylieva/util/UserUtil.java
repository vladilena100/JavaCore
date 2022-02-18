package com.solutions.vasylieva.util;

import com.solutions.vasylieva.dto.UserAddDTO;
import com.solutions.vasylieva.dto.UserEditDTO;
import com.solutions.vasylieva.model.User;

import java.util.Date;

public class UserUtil {

    private UserUtil() {}

    public static User toUser(UserAddDTO userAddDTO) {
        User user = getUser(userAddDTO.getLogin(), userAddDTO.getPassword(), userAddDTO.getEmail(), userAddDTO.getFirstName(), userAddDTO.getLastName(), userAddDTO.getBirthday());
        user.setRole(userAddDTO.getRole());
        return user;
    }

    public static User toUser(UserEditDTO userEdit, Long id) {
        User user = getUser(userEdit.getLogin(), userEdit.getPassword(), userEdit.getEmail(), userEdit.getFirstName(), userEdit.getLastName(), userEdit.getBirthday());
        user.setRole(userEdit.getRole());
        user.setId(id);
        return user;
    }

    private static User getUser(String login, String password, String email, String firstName, String lastName, Date birthday) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);
        return user;
    }
}
