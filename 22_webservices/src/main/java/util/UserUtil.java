package util;

import dto.UserAddDTO;
import dto.UserEditDTO;
import dto.UserRegisterDTO;
import model.User;

import java.util.Date;

public class UserUtil {

    private UserUtil() {}

    public static User toUser(UserRegisterDTO userRegisterDTO) {
        return getUser(userRegisterDTO.getLogin(), userRegisterDTO.getPassword(), userRegisterDTO.getEmail(), userRegisterDTO.getFirstName(), userRegisterDTO.getLastName(), userRegisterDTO.getBirthday());
    }

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

    public static UserEditDTO toUserEdit(User user) {
        UserEditDTO userEdit = new UserEditDTO();
        userEdit.setLogin(user.getLogin());
        userEdit.setEmail(user.getEmail());
        userEdit.setFirstName(user.getFirstName());
        userEdit.setLastName(user.getLastName());
        userEdit.setBirthday(user.getBirthday());
        userEdit.setRole(user.getRole());
        return userEdit;
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
