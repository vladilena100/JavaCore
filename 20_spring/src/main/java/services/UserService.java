package services;


import dao.Dao;
import dao.DaoUser;
import model.User;

import java.util.List;

public class UserService {

    private static UserService userService;

    private final DaoUser userDao;

    private UserService(Dao<User> userDao) {
        this.userDao = (DaoUser) userDao;
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public void create(User user) {
        userDao.create(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void remove(User user) {
        userDao.remove(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User getUserByLoginPassword(String login, String password) {

        User byLogin = userDao.findByLogin(login);

        if (byLogin != null && byLogin.getPassword().equals(password)) {
            return byLogin;
        }
        return null;
    }

    public static synchronized UserService getInstance(DaoUser userDao) {
        if (userService == null) {
            userService = new UserService(userDao);
        }
        return userService;
    }
}
