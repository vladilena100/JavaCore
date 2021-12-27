package services;

import dao.DaoUser;
import model.User;

import java.util.List;

public class UserService {

    private final DaoUser daoUser;

    public UserService(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    public User findById(Long id) {
        return daoUser.findById(id);
    }

    public void create(User user) {
        daoUser.create(user);
    }

    public void update(User user) {
        daoUser.update(user);
    }

    public void remove(User user) {
        daoUser.remove(user);
    }

    public List<User> findAll() {
        return daoUser.findAll();
    }

    public User findByLogin(String login) {
        return daoUser.findByLogin(login);
    }

    public User findByEmail(String email) {
        return daoUser.findByEmail(email);
    }

    public User getUserByLoginPassword(String login, String password) {

        User byLogin = daoUser.findByLogin(login);

        if (byLogin != null && byLogin.getPassword().equals(password)) {
            return byLogin;
        }
        return null;
    }
}
