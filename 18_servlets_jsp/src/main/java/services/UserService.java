package services;

import dao.DaoUser;
import dao.jdbc.JdbcUserDaoImpl;
import exception.FoundUserException;
import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import support.ConnectionManager;
import support.DBPoolConfig;

import java.util.List;

public class UserService implements DaoUser {

    private static final Logger LOG = LogManager.getLogger(UserService.class);

    private final DaoUser daoUser;

    public UserService(DaoUser daoUser) {
        this.daoUser = new JdbcUserDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties")));
    }

    @Override
    public User findById(Long id) {
        return daoUser.findById(id);
    }

    @Override
    public void create(User user) {

        if (daoUser.findByEmail(user.getEmail()) == null || daoUser.findByLogin(user.getLogin()) == null) {

            daoUser.create(user);
        } else {
            LOG.info("user with this login or email already exists ");
            throw new FoundUserException("user already exist");
        }
    }

    @Override
    public void update(User user) {
        daoUser.update(user);
    }

    @Override
    public void remove(User user) {
        daoUser.remove(user);
    }

    @Override
    public List<User> findAll() {
        return daoUser.findAll();
    }

    @Override
    public User findByLogin(String login) {
        return daoUser.findByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return daoUser.findByEmail(email);
    }

    public User getUserByLoginPassword(String login, String password) {

        User byLogin = daoUser.findByLogin(login);

        if (byLogin != null && password.equals(byLogin.getPassword())) {
            return byLogin;
        } else {
            LOG.error("such user does not exist");
            throw new FoundUserException("login or password is not correct");
        }
    }

    public boolean userIsExist(String login, String password) {

        boolean result = false;

        for (User user : daoUser.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public Role getRoleByLoginPassword(String login, String password) {
        Role result = new Role();

        for (User user : daoUser.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }

        return result;
    }
}
